package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The player converter.
 * Should be used to convert all objects that relate to player
 */
@Mapper(componentModel = "spring")
public interface PlayerConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playerName", source = "nickName")
    @Mapping(target = "club", ignore = true)
    @Mapping(target = "position", ignore = true)
    Player fromInputToEntity(RegisterPlayerInput input);

    @Mapping(target = "position", source = "position.name")
    PlayerOutput fromEntityToOutput(Player entity);
}
