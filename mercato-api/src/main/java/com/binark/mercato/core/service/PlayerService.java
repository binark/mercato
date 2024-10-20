package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Find players by club
     *
     * @param clubId   The club id
     * @param pageable Pagination parameters
     * @return {@link Page} of {@link Player}
     */
    Page<Player> findByClub(String clubId, Pageable pageable);

    /**
     * Find players
     *
     * @param pageable Pagination parameters
     * @return {@link Page} of {@link Player}
     */
    Page<Player> find(Pageable pageable);
}
