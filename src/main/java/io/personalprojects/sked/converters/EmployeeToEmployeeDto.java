package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.EmployeeDto;
import io.personalprojects.sked.persistence.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeDto extends AbstractConverter<Employee, EmployeeDto> {

    public EmployeeDto convert(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSupervisor_id(employee.getSupervisor().getId());
        employeeDto.setShift_id(employee.getShift().getId());
        return employeeDto;
    }
}
