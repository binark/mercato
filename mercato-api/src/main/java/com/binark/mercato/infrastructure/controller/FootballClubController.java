package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.core.usecase.CreateFootballClubUseCase;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.CreateFootballClubOutput;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The football club controller, to manage football club operations
 */
@OpenAPIDefinition(info = @Info(title = "Football club service"))
@RestController
@RequestMapping("/football-clubs")
@RequiredArgsConstructor
public class FootballClubController {

    private final CreateFootballClubUseCase createFootballClubUseCase;

    /**
     * Create football club endpoint
     *
     * @param requestInput {@link CreateFootballClubInput} The request input
     * @return {@link CreateFootballClubOutput} The created football club
     */
    @Operation(summary = "Create football club")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFootballClubOutput create(@Valid @RequestBody CreateFootballClubInput requestInput) {
        return createFootballClubUseCase.execute(requestInput);
    }
}
