package io.personalprojects.sked.controller;

import io.personalprojects.sked.command.EmployeeDto;
import io.personalprojects.sked.command.EstablishmentDto;
import io.personalprojects.sked.command.ShiftDto;
import io.personalprojects.sked.converters.*;
import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EstablishmentNotFoundException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.persistence.model.Establishment;
import io.personalprojects.sked.services.EstablishmentService;
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
@RequestMapping("/api/establishment")
public class RestEstablishmentController {

    private EmployeeToEmployeeDto employeeToEmployeeDto;
    private EmployeeDtoToEmployee employeeDtoToEmployee;
    private EstablishmentService establishmentService;
    private EstablishmentDtoToEstablishment establishmentDtoToEstablishment;
    private EstablishmentToEstablishmentDto establishmentToEstablishmentDto;
    private ShiftToShiftDto shiftToShiftDto;

    @Autowired
    public void setEmployeeDtoToEmployee(EmployeeDtoToEmployee employeeDtoToEmployee) {
        this.employeeDtoToEmployee = employeeDtoToEmployee;
    }

    @Autowired
    public void setShiftToShiftDto(ShiftToShiftDto shiftToShiftDto) {
        this.shiftToShiftDto = shiftToShiftDto;
    }

    @Autowired
    public void setEmployeeToEmployeeDto(EmployeeToEmployeeDto employeeToEmployeeDto) {
        this.employeeToEmployeeDto = employeeToEmployeeDto;
    }

    @Autowired
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @Autowired
    public void setEstablishmentDtoToEstablishment(EstablishmentDtoToEstablishment establishmentDtoToEstablishment) {
        this.establishmentDtoToEstablishment = establishmentDtoToEstablishment;
    }

    @Autowired
    public void setEstablishmentToEstablishmentDto(EstablishmentToEstablishmentDto establishmentToEstablishmentDto) {
        this.establishmentToEstablishmentDto = establishmentToEstablishmentDto;
    }

    @PostMapping({"{eid}/{sid}/employee"})
    public ResponseEntity<?> addEmployee(@PathVariable Integer eid, @Valid @RequestBody EmployeeDto employeeDto, @PathVariable Integer sid,BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
        if (bindingResult.hasErrors() || employeeDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            Employee employee = establishmentService.addEmployee(eid, sid, employeeDtoToEmployee.convert(employeeDto));

            UriComponents uriComponents = uriComponentsBuilder.path("/api/establishment/" + eid + "shift/" + sid + "/employee/" + employee.getId()).build();

            // set headers with the created path
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (EstablishmentNotFoundException | ShiftNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<?> addEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || establishmentDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Establishment savedEstablishment = establishmentService.save(establishmentDtoToEstablishment.convert(establishmentDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/establishment/" + savedEstablishment.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{eid}/employee")
    public ResponseEntity<List<EmployeeDto>> listEstablishmentEmployees(@PathVariable Integer eid) {

        Establishment establishment = establishmentService.get(eid);

        if (establishment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<EmployeeDto> employeeDtos = employeeToEmployeeDto.convert(establishment.getEmployees());

        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/{eid}/shift")
    public ResponseEntity<List<ShiftDto>> listEstablishmentShifts(@PathVariable Integer eid) {

        Establishment establishment = establishmentService.get(eid);

        if (establishment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ShiftDto> shiftDtos = shiftToShiftDto.convert(establishment.getShifts());
        //List<ShiftDto> shiftDtos = establishment.getShifts().stream().map(shift -> shiftToShiftDto.convert(shift)).collect(Collectors.toList());

        return new ResponseEntity<>(shiftDtos, HttpStatus.OK);
    }

    @PutMapping("/{eid}")
    public ResponseEntity<EstablishmentDto> editEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto, BindingResult bindingResult, @PathVariable Integer eid) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (establishmentDto.getId() != null && !establishmentDto.getId().equals(eid)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (establishmentService.get(eid) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        establishmentDto.setId(eid);

        establishmentService.save(establishmentDtoToEstablishment.convert(establishmentDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{eid}")
    public ResponseEntity<EstablishmentDto> deleteEstablishment(@PathVariable Integer eid) {

        try {

            establishmentService.delete(eid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (AssociationExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (EstablishmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eid}")
    public ResponseEntity<EstablishmentDto> showEstablishment(@PathVariable Integer eid) {

        Establishment establishment = establishmentService.get(eid);

        if (establishment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(establishmentToEstablishmentDto.convert(establishment), HttpStatus.OK);
    }
    @GetMapping({"/", ""})
    public ResponseEntity<List<EstablishmentDto>> listEstablishments(){

        List<EstablishmentDto> establishmentsDtos = establishmentToEstablishmentDto.convert(establishmentService.list());
        /*List<EstablishmentDto> establishmentsDtos = establishmentService.list().stream()
                .map(establishment -> establishmentToEstablishmentDto.convert(establishment))
                .collect(Collectors.toList());*/

        return new ResponseEntity<>(establishmentsDtos, HttpStatus.OK);

    }

}

