package com.binark.mercato.core.service.impl;

import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.domain.entity.FootballClub;
import com.binark.mercato.exception.NotFoundException;
import com.binark.mercato.infrastructure.repository.FootballClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<FootballClub> search(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public FootballClub getClub(String id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("The football club is not found"));
    }
}
