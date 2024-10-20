package com.binark.mercato.core.service.impl;

import com.binark.mercato.domain.entity.PlayerPosition;
import com.binark.mercato.exception.NotFoundException;
import com.binark.mercato.infrastructure.repository.PlayerPositionRepository;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class PlayerPositionServiceImplTest {

    public static final String POSITION_NAME = "STRIKER";
    @Mock
    private PlayerPositionRepository repository;
    @InjectMocks
    private PlayerPositionServiceImpl playerPositionService;

    @Test
    void find() {
        List<PlayerPosition> playerPositions = List.of(PlayerPosition.builder().name(POSITION_NAME).build());
        Mockito.when(repository.findAll()).thenReturn(playerPositions);

        Set<PlayerPosition> result = playerPositionService.find();

        Assertions.assertThat(result).hasSizeLessThanOrEqualTo(playerPositions.size());
        Assertions.assertThat(playerPositions)
                  .extracting(PlayerPosition::getName)
                  .containsAll(result.stream().map(PlayerPosition::getName).collect(Collectors.toSet()));
    }

    @Test
    @SneakyThrows
    void getExistingPosition() {
        PlayerPosition expectedResult = PlayerPosition.builder().name(POSITION_NAME).build();
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(expectedResult));
        String id = UUID.randomUUID().toString();

        PlayerPosition result = playerPositionService.getPosition(id);

        Assertions.assertThat(result)
                  .isNotNull()
                  .isEqualTo(expectedResult);

        Mockito.verify(repository).findById(id);
    }

    @Test
    @SneakyThrows
    void getNonExistingPosition() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        String id = UUID.randomUUID().toString();

        Assertions.assertThatThrownBy(() -> playerPositionService.getPosition(id))
                  .isInstanceOf(NotFoundException.class)
                  .hasFieldOrPropertyWithValue("code", "NOT FOUND");

        Mockito.verify(repository).findById(id);
    }
}