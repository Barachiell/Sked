package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EstablishmentNotFoundException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.persistence.model.Establishment;
import io.personalprojects.sked.persistence.model.Shift;

import java.util.List;

public interface EstablishmentService {
    Establishment get(Integer id);

    Establishment save(Establishment establishment);

    void delete(Integer id) throws AssociationExistsException, EstablishmentNotFoundException;

    List<Establishment> list();
    List<Shift> listShift(Integer id);
    List<Employee> listEmployee(Integer id);
    Employee addEmployee(Integer id, Integer sid, Employee employee) throws EstablishmentNotFoundException, ShiftNotFoundException;
}
