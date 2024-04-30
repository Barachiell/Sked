package io.personalprojects.sked.persistence.dao.jpa;

import io.personalprojects.sked.persistence.dao.EstablishmentDao;
import io.personalprojects.sked.persistence.model.Establishment;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEstablishmentDao extends GenericJpaDao<Establishment> implements EstablishmentDao {

    public JpaEstablishmentDao(){
        super(Establishment.class);
    }
}
