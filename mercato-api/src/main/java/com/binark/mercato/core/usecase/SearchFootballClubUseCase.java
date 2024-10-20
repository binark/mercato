package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.output.FootballClubOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Search football club use case
 */
public interface SearchFootballClubUseCase {

    /**
     * Execute search football club use case
     *
     * @param pageable {@link Pageable} pagination parameters
     * @return {@link Page} of {@link FootballClubOutput}
     */
    Page<FootballClubOutput> execute(Pageable pageable);
}
