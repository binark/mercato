package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.PlayerPosition;
import com.binark.mercato.exception.NotFoundException;

import java.util.Set;

/**
 * Player position service
 */
public interface PlayerPositionService {

    /**
     * Find all player position
     *
     * @return {@link Set} of {@link PlayerPosition}
     */
    Set<PlayerPosition> find();

    /**
     * Find a player position by id
     *
     * @param id The player position id
     * @return {@link PlayerPosition} The retrieved player position
     * @throws NotFoundException If player poosition is not found
     */
    PlayerPosition getPosition(String id) throws NotFoundException;
}
