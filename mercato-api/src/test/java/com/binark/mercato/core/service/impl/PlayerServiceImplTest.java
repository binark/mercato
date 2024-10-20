package com.binark.mercato.core.service.impl;

import com.binark.mercato.domain.entity.Player;
import com.binark.mercato.infrastructure.repository.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository repository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void save() {
        Player expectedResponse = Player.builder().id(UUID.randomUUID().toString()).build();
        Mockito.when(repository.save(Mockito.any(Player.class))).thenReturn(expectedResponse);
        Player toSave = Player.builder().build();
        Player response = playerService.save(toSave);

        Assertions.assertThat(response).isNotNull().isEqualTo(expectedResponse);

        Mockito.verify(repository).save(toSave);
    }
}