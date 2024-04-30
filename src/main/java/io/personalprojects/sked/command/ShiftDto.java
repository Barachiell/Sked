package io.personalprojects.sked.command;

import java.sql.Time;

public class ShiftDto {
    private Integer id;
    private String shift;
    private Time entry_hour;
    private Time leave_hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
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
}
