package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Search football club players use case
 */
public interface SearchFootballClubPlayersUseCase {

    /**
     * Execute search football club players use case
     *
     * @param id       The football club id
     * @param pageable {@link Pageable} pagination parameters
     * @return {@link Page} of {@link FootballClubOutput}
     */
    Page<PlayerOutput> execute(String id, Pageable pageable);
}
