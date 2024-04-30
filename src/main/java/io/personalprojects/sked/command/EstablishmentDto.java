package io.personalprojects.sked.command;

import java.sql.Time;

public class EstablishmentDto {
    private Integer id;
    private String name;
    private String sector;
    private Time opening_hour;
    private Time closing_hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}

