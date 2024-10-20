package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.core.usecase.CreateFootballClubUseCase;
import com.binark.mercato.core.usecase.SearchFootballClubUseCase;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
