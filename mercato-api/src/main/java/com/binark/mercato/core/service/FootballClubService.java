package com.binark.mercato.core.service;

import com.binark.mercato.domain.entity.FootballClub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Get the list of football clubs following the pagination parameters
     *
     * @param pageable {@link Pageable} Pagination parameters
     * @return {@link Page} of {@link FootballClub}
     */
    Page<FootballClub> search(Pageable pageable);
}
