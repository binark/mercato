package com.binark.mercato.core.service.impl;

import com.binark.mercato.core.service.PlayerPositionService;
import com.binark.mercato.domain.entity.PlayerPosition;
import com.binark.mercato.exception.NotFoundException;
import com.binark.mercato.infrastructure.repository.PlayerPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlayerPositionServiceImpl implements PlayerPositionService {

    private final PlayerPositionRepository repository;

    @Override
    public Set<PlayerPosition> find() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public PlayerPosition getPosition(String id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("The position doesn't exists"));
    }
}
