package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The football club converter.
 * Should be used to convert all objects that relate to football club
 */
@Mapper(componentModel = "spring")
public interface FootballClubConverter {

    @Mapping(target = "id", ignore = true)
    FootballClub fromInputToEntity(CreateFootballClubInput input);

    FootballClubOutput fromEntityToOutput(FootballClub entity);
}
