package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EmployeeNotFoundException;
import io.personalprojects.sked.persistence.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee get(Integer id);
    Employee save(Employee employee);
    void delete(Integer id) throws AssociationExistsException, EmployeeNotFoundException;
    List<Employee> list();
}
