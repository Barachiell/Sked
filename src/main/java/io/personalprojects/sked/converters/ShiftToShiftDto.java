package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.ShiftDto;
import io.personalprojects.sked.persistence.model.Shift;
import org.springframework.stereotype.Component;

@Component
public class ShiftToShiftDto extends AbstractConverter<Shift, ShiftDto> {

    public ShiftDto convert(Shift shift) {
        ShiftDto shiftDto = new ShiftDto();

        shiftDto.setId(shift.getId());
        shiftDto.setShift(shift.getShift());
        shiftDto.setEntry_hour(shift.getEntry_hour());
        shiftDto.setLeave_hour(shift.getLeave_hour());

        return shiftDto;
    }
}
