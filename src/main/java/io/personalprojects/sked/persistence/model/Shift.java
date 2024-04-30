package io.personalprojects.sked.persistence.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shifts")
public class Shift extends AbstractModel{
    private String shift;
    private Time entry_hour;
    private Time leave_hour;
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "shift"
    )
    private List<Employee> employees = new ArrayList<>();
    @ManyToOne
    private Establishment establishment;

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Time getEntry_hour() {
        return entry_hour;
    }

    public void setEntry_hour(Time entry_hour) {
        this.entry_hour = entry_hour;
    }

    public Time getLeave_hour() {
        return leave_hour;
    }

    public void setLeave_hour(Time leave_hour) {
        this.leave_hour = leave_hour;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setShift(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setShift(null);
    }
}
