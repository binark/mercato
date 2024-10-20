package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;

/**
 * Create football club use case
 */
public interface CreateFootballClubUseCase {

    /**
     * Execute create football club use case
     *
     * @param input {@link CreateFootballClubInput} The data input
     * @return {@link FootballClubOutput} The created football club
     */
    FootballClubOutput execute(CreateFootballClubInput input);
}
