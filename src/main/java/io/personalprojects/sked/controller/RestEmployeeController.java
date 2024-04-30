package io.personalprojects.sked.controller;

import io.personalprojects.sked.command.EmployeeDto;
import io.personalprojects.sked.command.ShiftDto;
import io.personalprojects.sked.converters.EmployeeDtoToEmployee;
import io.personalprojects.sked.converters.EmployeeToEmployeeDto;
import io.personalprojects.sked.converters.ShiftToShiftDto;
import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EmployeeNotFoundException;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {

    private EmployeeService employeeService;
    private EmployeeDtoToEmployee employeeDtoToEmployee;
    private EmployeeToEmployeeDto employeeToEmployeeDto;
    private ShiftToShiftDto shiftToShiftDto;

    @Autowired
    public void setShiftToShiftDto(ShiftToShiftDto shiftToShiftDto) {
        this.shiftToShiftDto = shiftToShiftDto;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setEmployeeDtoToEmployee(EmployeeDtoToEmployee employeeDtoToEmployee) {
        this.employeeDtoToEmployee = employeeDtoToEmployee;
    }

    @Autowired
    public void setEmployeeToEmployeeDto(EmployeeToEmployeeDto employeeToEmployeeDto) {
        this.employeeToEmployeeDto = employeeToEmployeeDto;
    }

    /**
     * Retrieves a representation of the list of employees
     *
     * @return the response entity
     */
    @GetMapping({"/", ""})
    public ResponseEntity<List<EmployeeDto>> listEmployees(){

        List<EmployeeDto> employeeDtos = employeeToEmployeeDto.convert(employeeService.list());

        /*List<EmployeeDto> employeeDtos = employeeService.list().stream()
                .map(employee -> employeeToEmployeeDto.convert(employee))
                .collect(Collectors.toList());*/

        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);

    }

    /**
     * Retrieves a representation of the given employee
     *
     * @param id the employee id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> showEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.get(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeToEmployeeDto.convert(employee), HttpStatus.OK);
    }


    /**
     * Edits an employee
     *
     * @param employeeDto   the employee DTO
     * @param bindingResult the binding result
     * @param id            the employee id
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (employeeDto.getId() != null && !employeeDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (employeeService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeDto.setId(id);

        employeeService.save(employeeDtoToEmployee.convert(employeeDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes an employee
     *
     * @param id the employee id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Integer id) {

        try {

            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (AssociationExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a representation of the employee's shift
     *
     * @param id the employee id
     * @return the response entity
     */
    @GetMapping("/{id}/shift")
    public ResponseEntity<ShiftDto> getEmployeeShift(@PathVariable Integer id){
        Employee employee = employeeService.get(id);

        if(employee == null || employee.getShift() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shiftToShiftDto.convert(employee.getShift()),HttpStatus.OK);
    }

}

