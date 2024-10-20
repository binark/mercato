package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.FootballClubConverter;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.core.usecase.CreateFootballClubUseCase;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.CreateFootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateFootballClubUseCaseImpl implements CreateFootballClubUseCase {

    private final FootballClubService footballClubService;
    private final FootballClubConverter converter;

    @Override
    @Transactional
    public CreateFootballClubOutput execute(CreateFootballClubInput input) {
        FootballClub footballClub = converter.fromInputToEntity(input);
        footballClub = footballClubService.save(footballClub);
        return converter.fromEntityToOutput(footballClub);
    }
}
