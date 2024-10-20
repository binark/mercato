package com.binark.mercato.core.service.impl;

import com.binark.mercato.core.service.PlayerService;
import com.binark.mercato.domain.entity.Player;
import com.binark.mercato.infrastructure.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Player save(Player player) {
        return repository.save(player);
    }
}
