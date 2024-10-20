package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerPositionConverter;
import com.binark.mercato.core.service.PlayerPositionService;
import com.binark.mercato.core.usecase.FindPlayerPositionUseCase;
import com.binark.mercato.domain.dto.output.PlayerPositionOutput;
import com.binark.mercato.domain.entity.PlayerPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindPlayerPositionUseCaseImpl implements FindPlayerPositionUseCase {

    private final PlayerPositionService playerPositionService;
    private final PlayerPositionConverter converter;

    @Override
    public Set<PlayerPositionOutput> execute() {
        Set<PlayerPosition> playerPositions = playerPositionService.find();
        return playerPositions.stream().map(converter::fromEntityToOutput).collect(Collectors.toSet());
    }
}
