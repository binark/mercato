package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.output.PlayerPositionOutput;
import com.binark.mercato.domain.entity.PlayerPosition;
import org.mapstruct.Mapper;

/**
 * The player position converter.
 * Should be used to convert all objects that relate to player position
 */
@Mapper(componentModel = "spring")
public interface PlayerPositionConverter {

    PlayerPositionOutput fromEntityToOutput(PlayerPosition entity);
}
