package io.personalprojects.sked.persistence.dao.jpa;

import io.personalprojects.sked.persistence.dao.ShiftDao;
import io.personalprojects.sked.persistence.model.Shift;
import org.springframework.stereotype.Repository;

@Repository
public class JpaShiftDao extends GenericJpaDao<Shift> implements ShiftDao {

    public JpaShiftDao(){
        super(Shift.class);
    }
}
