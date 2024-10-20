package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.Player;

/**
 * Player service
 */
public interface PlayerService {

    /**
     * Save a new player
     *
     * @param player The player to save
     * @return {@link Player} The saved player
     */
    Player save(Player player);
}
