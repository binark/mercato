package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.core.usecase.CreateFootballClubUseCase;
import com.binark.mercato.core.usecase.SearchFootballClubPlayersUseCase;
import com.binark.mercato.core.usecase.SearchFootballClubUseCase;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final SearchFootballClubUseCase searchFootballClubUseCase;
    private final SearchFootballClubPlayersUseCase searchFootballClubPlayersUseCase;

    /**
     * Create football club endpoint
     *
     * @param requestInput {@link CreateFootballClubInput} The request input
     * @return {@link FootballClubOutput} The created football club
     */
    @Operation(summary = "Create football club")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FootballClubOutput create(@Valid @RequestBody CreateFootballClubInput requestInput) {
        return createFootballClubUseCase.execute(requestInput);
    }

    /**
     * Search football clubs endpoint
     *
     * @param pageable {@link Pageable} The pagination parameters
     * @return {@link Page} of {@link FootballClubOutput}
     */
    @Operation(summary = "Search football clubs")
    @GetMapping
    public Page<FootballClubOutput> search(Pageable pageable) {
        return searchFootballClubUseCase.execute(pageable);
    }

    /**
     * Search players for a club endpoint
     *
     * @param pageable {@link Pageable} The pagination parameters
     * @return {@link Page} of {@link FootballClubOutput}
     */
    @Operation(summary = "Search players for a club")
    @GetMapping("/{id}/players")
    public Page<PlayerOutput> searchPlayers(@PathVariable @NotNull String id, @ParameterObject Pageable pageable) {
        return searchFootballClubPlayersUseCase.execute(id, pageable);
    }
}
