package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

    /**
     * Find player using Spring Query Specifications
     *
     * @param specification {@link Specification} The query specifications
     * @param pageable      The pagination parameters
     * @return {@link Page} of {@link Player}
     */
    Page<Player> find(Specification<Player> specification, Pageable pageable);
}
