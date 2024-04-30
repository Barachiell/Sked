package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EstablishmentNotFoundException;
import io.personalprojects.sked.persistence.dao.EstablishmentDao;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.persistence.model.Establishment;
import io.personalprojects.sked.persistence.model.Shift;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class EstablishmentServiceImplTest {

    private EstablishmentDao establishmentDao;
    private EstablishmentServiceImpl establishmentService;

    @Before
    public void setup() {
        establishmentDao = mock(EstablishmentDao.class);
        establishmentService = new EstablishmentServiceImpl();
        establishmentService.setEstablishmentDao(establishmentDao);
    }

    @Test
    public void testGet() {
        int id = 333;

        Establishment establishment = mock(Establishment.class);
        when(establishmentDao.findById(id)).thenReturn(establishment);
        when(establishment.getId()).thenReturn(id);

        Establishment returnEstablishment = establishmentService.get(id);

        verify(establishmentDao, times(1)).findById(id);
        assertEquals(returnEstablishment.getId().intValue(), id);
    }

    @Test
    public void testSave() {
        Establishment establishment = mock(Establishment.class);

        when(establishmentDao.saveOrUpdate(establishment)).thenReturn(establishment);

        Establishment returnEstablishment = establishmentService.save(establishment);

        assertNotNull(returnEstablishment);
        verify(establishmentDao, times(1)).saveOrUpdate(establishment);
    }

    @Test
    public void testDelete() throws EstablishmentNotFoundException, AssociationExistsException {
        int id = 333;

        Establishment establishment = mock(Establishment.class);
        establishment.setId(id);
        when(establishmentDao.findById(id)).thenReturn(establishment);

        establishmentService.delete(id);

        verify(establishmentDao, times(1)).delete(id);
    }

    @Test
    public void testList() {
        List<Establishment> list = mock(List.class);
        when(establishmentDao.findAll()).thenReturn(list);

        List<Establishment> returnList = establishmentService.list();

        assertNotNull(returnList);
        verify(establishmentDao, times(1)).findAll();
    }

    @Test
    public void testListShift() {
        int id = 333;
        Establishment establishment = mock(Establishment.class);
        when(establishmentDao.findById(id)).thenReturn(establishment);

        List<Shift> returnList = establishmentService.listShift(id);

        assertNotNull(returnList);
        verify(establishment, times(1)).getShifts();
    }

    @Test
    public void testListEmployee() {
        int id = 333;
        Establishment establishment = mock(Establishment.class);
        when(establishmentDao.findById(id)).thenReturn(establishment);

        List<Employee> returnList = establishmentService.listEmployee(id);

        assertNotNull(returnList);
        verify(establishment, times(1)).getEmployees();
    }

}
