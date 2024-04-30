package io.personalprojects.sked.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends AbstractModel{
    private String name;
    @ManyToOne
    private Shift shift;
    private String department;
    @ManyToOne
    private Establishment establishment;
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Employee getSupervisor() {
        if(supervisor == null) {
            return this;
        } else {
            return supervisor;
        }
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
}
