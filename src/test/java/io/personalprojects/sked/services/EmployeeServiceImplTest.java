package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EmployeeNotFoundException;
import io.personalprojects.sked.persistence.dao.EmployeeDao;
import io.personalprojects.sked.persistence.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {
    private EmployeeDao employeeDao;
    private EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        employeeDao = mock(EmployeeDao.class);
        employeeService = new EmployeeServiceImpl();
        employeeService.setEmployeeDao(employeeDao);
    }

    @Test
    public void testGet() {
        int id = 333;
        Employee employee = mock(Employee.class);
        when(employeeDao.findById(id)).thenReturn(employee);
        when(employee.getId()).thenReturn(id);

        Employee returnEmployee = employeeService.get(id);

        verify(employeeDao, times(1)).findById(id);
        assertEquals(returnEmployee.getId().intValue(), id);
    }

    @Test
    public void testSave() {
        Employee employee = mock(Employee.class);
        when(employeeDao.saveOrUpdate(employee)).thenReturn(employee);

        Employee returnEmployee = employeeService.save(employee);

        verify(employeeDao, times(1)).saveOrUpdate(employee);
        assertNotNull(returnEmployee);
    }

    @Test
    public void testDelete() throws AssociationExistsException, EmployeeNotFoundException {
        int id = 333;
        Employee employee = mock(Employee.class);
        employee.setId(id);
        when(employeeDao.findById(id)).thenReturn(employee);

        employeeService.delete(id);

        verify(employeeDao, times(1)).delete(id);
    }

    @Test
    public void testList() {
        List<Employee> list = mock(List.class);
        when(employeeDao.findAll()).thenReturn(list);

        List<Employee> returnList = employeeService.list();

        assertNotNull(returnList);
        verify(employeeDao, times(1)).findAll();
    }
}
