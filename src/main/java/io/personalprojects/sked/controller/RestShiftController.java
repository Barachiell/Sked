package io.personalprojects.sked.controller;

import io.personalprojects.sked.command.ShiftDto;
import io.personalprojects.sked.converters.ShiftDtoToShift;
import io.personalprojects.sked.converters.ShiftToShiftDto;
import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.model.Shift;
import io.personalprojects.sked.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shift")
public class RestShiftController {

    private ShiftToShiftDto shiftToShiftDto;
    private ShiftDtoToShift shiftDtoToShift;
    private ShiftService shiftService;

    @Autowired
    public void setShiftToShiftDto(ShiftToShiftDto shiftToShiftDto) {
        this.shiftToShiftDto = shiftToShiftDto;
    }

    @Autowired
    public void setShiftDtoToShift(ShiftDtoToShift shiftDtoToShift) {
        this.shiftDtoToShift = shiftDtoToShift;
    }

    @Autowired
    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PutMapping("/{sid}")
    public ResponseEntity<ShiftDto> editShift(@Valid @RequestBody ShiftDto shiftDto, BindingResult bindingResult, @PathVariable Integer sid) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (shiftDto.getId() != null && !shiftDto.getId().equals(sid)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (shiftService.get(sid) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        shiftDto.setId(sid);

        shiftService.save(shiftDtoToShift.convert(shiftDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<?> addShift(@Valid @RequestBody ShiftDto shiftDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || shiftDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Shift savedShift = shiftService.save(shiftDtoToShift.convert(shiftDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/shift/" + savedShift.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sid}")
    public ResponseEntity<ShiftDto> deleteShift(@PathVariable Integer sid) {

        try {
            shiftService.delete(sid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (AssociationExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (ShiftNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a representation of the list of shifts
     *
     * @return the response entity
     */
    @GetMapping({"/", ""})
    public ResponseEntity<List<ShiftDto>> listShifts(){

        List<ShiftDto> shiftDtos = shiftToShiftDto.convert(shiftService.list());
        /*List<ShiftDto> shiftDtos = shiftService.list().stream()
                .map(shift -> shiftToShiftDto.convert(shift))
                .collect(Collectors.toList());*/

        return new ResponseEntity<>(shiftDtos, HttpStatus.OK);

    }

}

