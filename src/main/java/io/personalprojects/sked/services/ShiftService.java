package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.model.Shift;

import java.util.List;

public interface ShiftService {
    Shift get(Integer id);
    Shift save(Shift shift);
    void delete(Integer id) throws AssociationExistsException, ShiftNotFoundException;
    List<Shift> list();
}
