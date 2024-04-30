package io.personalprojects.sked.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "establishments")
public class Establishment extends AbstractModel{

    private String name;
    private String sector;
    private Time opening_hour;
    private Time closing_hour;
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "establishment"
    )
    private List<Shift> shifts = new ArrayList<>();
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "establishment"
    )
    private List<Employee> employees = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Time getOpening_hour() {
        return opening_hour;
    }

    public void setOpening_hour(Time opening_hour) {
        this.opening_hour = opening_hour;
    }

    public Time getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(Time closing_hour) {
        this.closing_hour = closing_hour;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void addShift(Shift shift) {
        shifts.add(shift);
        shift.setEstablishment(this);
    }
    public void removeShift(Shift shift){
        shifts.remove(shift);
        shift.setEstablishment(null);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setEstablishment(this);
    }
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setEstablishment(null);
    }
}
