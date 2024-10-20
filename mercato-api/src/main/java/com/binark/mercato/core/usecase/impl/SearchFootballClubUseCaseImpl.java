package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.FootballClubConverter;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.core.usecase.SearchFootballClubUseCase;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchFootballClubUseCaseImpl implements SearchFootballClubUseCase {

    private final FootballClubService footballClubService;
    private final FootballClubConverter converter;

    @Override
    public Page<FootballClubOutput> execute(Pageable pageable) {
        Page<FootballClub> footballClubPage = footballClubService.search(pageable);
        return footballClubPage.map(converter::fromEntityToOutput);
    }
}
