package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.ShiftDto;
import io.personalprojects.sked.persistence.model.Shift;
import io.personalprojects.sked.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ShiftDtoToShift implements Converter<ShiftDto, Shift> {
    private ShiftService shiftService;

    @Autowired
    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    public Shift convert(ShiftDto shiftDto) {
        Shift shift = shiftDto.getId() != null ? shiftService.get(shiftDto.getId()) : new Shift();

        shift.setShift(shiftDto.getShift());
        shift.setEntry_hour(shiftDto.getEntry_hour());
        shift.setLeave_hour(shiftDto.getLeave_hour());

        return shift;
    }
}
