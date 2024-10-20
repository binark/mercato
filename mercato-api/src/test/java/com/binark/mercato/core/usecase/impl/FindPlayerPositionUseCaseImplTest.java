package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerPositionConverterImpl;
import com.binark.mercato.core.service.PlayerPositionService;
import com.binark.mercato.domain.dto.output.PlayerPositionOutput;
import com.binark.mercato.domain.entity.PlayerPosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class FindPlayerPositionUseCaseImplTest {

    @Mock
    private PlayerPositionService playerPositionService;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private PlayerPositionConverterImpl converter;
    @InjectMocks
    private FindPlayerPositionUseCaseImpl findPlayerPositionUseCase;

    @Test
    void execute() {
        Set<PlayerPosition> serviceResponse = Set.of(PlayerPosition.builder().name("STRIKER").build());
        Mockito.when(playerPositionService.find()).thenReturn(serviceResponse);

        Set<PlayerPositionOutput> response = findPlayerPositionUseCase.execute();

        Assertions.assertThat(response)
                  .isNotEmpty()
                  .hasSameSizeAs(serviceResponse);
        Assertions.assertThat(response.stream().map(PlayerPositionOutput::getName).toList())
                  .usingRecursiveAssertion()
                  .isEqualTo(serviceResponse.stream().map(PlayerPosition::getName).toList());

        Mockito.verify(playerPositionService).find();
        Mockito.verify(converter, Mockito.times(serviceResponse.size())).fromEntityToOutput(Mockito.any(PlayerPosition.class));
    }
}