package com.binark.mercato.core.service.impl;

import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.domain.entity.FootballClub;
import com.binark.mercato.infrastructure.repository.FootballClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FootballClubServiceImpl implements FootballClubService {

    private final FootballClubRepository repository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public FootballClub save(FootballClub footballClub) {
        return repository.save(footballClub);
    }
}
