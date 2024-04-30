package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EmployeeNotFoundException;
import io.personalprojects.sked.persistence.dao.EmployeeDao;
import io.personalprojects.sked.persistence.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee get(Integer id) {
        return employeeDao.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDao.saveOrUpdate(employee);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws AssociationExistsException, EmployeeNotFoundException {
        employeeDao.delete(id);
    }

    @Override
    public List<Employee> list() {
        return employeeDao.findAll();
    }
}
