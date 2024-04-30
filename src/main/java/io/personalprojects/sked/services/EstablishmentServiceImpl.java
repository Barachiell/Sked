package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.EstablishmentNotFoundException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.dao.EstablishmentDao;
import io.personalprojects.sked.persistence.model.Employee;
import io.personalprojects.sked.persistence.model.Establishment;
import io.personalprojects.sked.persistence.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private EstablishmentDao establishmentDao;
    private ShiftService shiftService;


    @Autowired
    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @Autowired
    public void setEstablishmentDao(EstablishmentDao establishmentDao) {
        this.establishmentDao = establishmentDao;
    }
    @Override
    public Establishment get(Integer id) {
        return establishmentDao.findById(id);
    }
    @Transactional
    @Override
    public Establishment save(Establishment establishment) {
        return establishmentDao.saveOrUpdate(establishment);
    }
    @Transactional
    @Override
    public void delete(Integer id) throws AssociationExistsException, EstablishmentNotFoundException {
        establishmentDao.delete(id);
    }
    @Override
    public List<Establishment> list() {
        return establishmentDao.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public List<Shift> listShift(Integer id){

        Establishment establishment = Optional.ofNullable(establishmentDao.findById(id))
                .orElseThrow(RuntimeException::new);

        return new ArrayList<>(establishment.getShifts());
    }
    @Transactional(readOnly = true)
    @Override
    public List<Employee> listEmployee(Integer id) {

        Establishment establishment = Optional.ofNullable((establishmentDao.findById(id)))
                .orElseThrow(RuntimeException::new);

        return new ArrayList<>(establishment.getEmployees());
    }

    @Transactional
    @Override
    public Employee addEmployee(Integer id, Integer sid, Employee employee) throws EstablishmentNotFoundException, ShiftNotFoundException {

        Establishment establishment = Optional.ofNullable(establishmentDao.findById(id))
                .orElseThrow(EstablishmentNotFoundException::new);

        Shift shift = Optional.ofNullable(shiftService.get(sid))
                .orElseThrow(ShiftNotFoundException::new);

        employee.setShift(shift);
        establishment.addEmployee(employee);
        establishmentDao.saveOrUpdate(establishment);

        return establishment.getEmployees().get(establishment.getEmployees().size() - 1);
    }
}
