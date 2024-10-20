package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.core.usecase.FindPlayerPositionUseCase;
import com.binark.mercato.domain.dto.output.PlayerPositionOutput;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@OpenAPIDefinition(info = @Info(title = "Player position service"))
@RestController
@RequestMapping("/player-positions")
@RequiredArgsConstructor
public class PlayerPositionController {

    private final FindPlayerPositionUseCase findPlayerPositionUseCase;

    @Operation(summary = "Find all player positions")
    @GetMapping
    public Set<PlayerPositionOutput> find() {
        return findPlayerPositionUseCase.execute();
    }
}
