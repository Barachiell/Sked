package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.dao.ShiftDao;
import io.personalprojects.sked.persistence.model.Shift;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShiftServiceImplTest {
    private ShiftDao shiftDao;
    private ShiftServiceImpl shiftService;

    @Before
    public void setup() {
        shiftDao = mock(ShiftDao.class);
        shiftService = new ShiftServiceImpl();
        shiftService.setShiftDao(shiftDao);
    }

    @Test
    public void testGet() {
        int id = 333;

        Shift shift = mock(Shift.class);
        when(shiftDao.findById(id)).thenReturn(shift);
        when(shift.getId()).thenReturn(id);

        Shift returnShift = shiftService.get(id);

        verify(shiftDao, times(1)).findById(id);
        assertEquals(returnShift.getId().intValue(), id);
    }

    @Test
    public void testFailToGet() {
        int id = 333;
        when(shiftDao.findById(id)).thenReturn(null);

        Shift returnShift = shiftService.get(id);

        assertNull(returnShift);
    }

    @Test
    public void testSave() {
        Shift shift = mock(Shift.class);

        when(shiftDao.saveOrUpdate(shift)).thenReturn(shift);

        Shift returnShift = shiftService.save(shift);

        assertNotNull(returnShift);
        verify(shiftDao, times(1)).saveOrUpdate(shift);
    }

    @Test
    public void testDelete() throws ShiftNotFoundException, AssociationExistsException {
        int id = 333;

        Shift shift = mock(Shift.class);
        shift.setId(id);
        when(shiftDao.findById(id)).thenReturn(shift);

        shiftService.delete(id);

        verify(shiftDao, times(1)).delete(id);
    }

    @Test
    public void testList() {
        List<Shift> list = mock(List.class);

        when(shiftDao.findAll()).thenReturn(list);

        List<Shift> returnList = shiftService.list();

        assertNotNull(returnList);
        verify(shiftDao, times(1)).findAll();
    }
}
