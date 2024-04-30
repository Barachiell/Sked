package io.personalprojects.sked.converters;

import io.personalprojects.sked.command.EstablishmentDto;
import io.personalprojects.sked.persistence.model.Establishment;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentToEstablishmentDto extends AbstractConverter<Establishment, EstablishmentDto>{

    public EstablishmentDto convert(Establishment establishment) {
        EstablishmentDto establishmentDto = new EstablishmentDto();

        establishmentDto.setId(establishment.getId());
        establishmentDto.setName(establishment.getName());
        establishmentDto.setSector(establishment.getSector());
        establishmentDto.setOpening_hour(establishment.getOpening_hour());
        establishmentDto.setClosing_hour(establishment.getClosing_hour());

        return establishmentDto;
    }
}
