package io.personalprojects.sked.persistence.dao.jpa;

import io.personalprojects.sked.persistence.dao.EmployeeDao;
import io.personalprojects.sked.persistence.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEmployeeDao extends GenericJpaDao<Employee> implements EmployeeDao{

    public JpaEmployeeDao() {
        super(Employee.class);
    }
}
