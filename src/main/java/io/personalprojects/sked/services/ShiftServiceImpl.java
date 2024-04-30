package io.personalprojects.sked.services;

import io.personalprojects.sked.exceptions.AssociationExistsException;
import io.personalprojects.sked.exceptions.ShiftNotFoundException;
import io.personalprojects.sked.persistence.dao.ShiftDao;
import io.personalprojects.sked.persistence.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService{

    private ShiftDao shiftDao;
    @Autowired
    public void setShiftDao(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    @Override
    public Shift get(Integer id) {
        return shiftDao.findById(id);
    }

    @Transactional
    @Override
    public Shift save(Shift shift) {
        return shiftDao.saveOrUpdate(shift);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws AssociationExistsException, ShiftNotFoundException {
        shiftDao.delete(id);
    }

    @Override
    public List<Shift> list() {
        return shiftDao.findAll();
    }
}
