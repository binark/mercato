package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.FootballClub;

/**
 * Football club service
 */
public interface FootballClubService {

    /**
     * Save new football club
     *
     * @param footballClub The football club entity to save
     * @return {@link FootballClub} The saved football club
     */
    FootballClub save(FootballClub footballClub);
}
