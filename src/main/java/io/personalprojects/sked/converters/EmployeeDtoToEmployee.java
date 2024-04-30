package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.EmployeeDto;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.services.EmployeeService;
import io.personalprojects.sked.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoToEmployee implements Converter<EmployeeDto, Employee> {

    private EmployeeService employeeService;
    private ShiftService shiftService;

    @Autowired
    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee convert(EmployeeDto employeeDto) {
        Employee employee = employeeDto.getId() != null ? employeeService.get(employeeDto.getId()) : new Employee();

        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSupervisor(employeeService.get(employeeDto.getSupervisor_id()));
        employee.setShift(shiftService.get(employeeDto.getShift_id()));
        return employee;
    }
}
