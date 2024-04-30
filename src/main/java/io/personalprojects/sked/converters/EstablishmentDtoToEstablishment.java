package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.EstablishmentDto;
import io.personalprojects.sked.persistence.model.Establishment;
import io.personalprojects.sked.services.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentDtoToEstablishment implements Converter<EstablishmentDto, Establishment> {

    public EstablishmentService establishmentService;

    @Autowired
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    public Establishment convert(EstablishmentDto establishmentDto) {
        Establishment establishment = establishmentDto.getId() != null ? establishmentService.get(establishmentDto.getId()) : new Establishment();

        establishment.setName(establishmentDto.getName());
        establishment.setSector(establishmentDto.getSector());
        establishment.setOpening_hour(establishmentDto.getOpening_hour());
        establishment.setClosing_hour(establishmentDto.getClosing_hour());

        return establishment;
    }
}
