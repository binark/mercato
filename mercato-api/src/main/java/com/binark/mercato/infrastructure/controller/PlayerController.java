package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.core.usecase.RegisterPlayerUseCase;
import com.binark.mercato.core.usecase.SearchPlayersUserCase;
import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.exception.MercatoApiException;
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
 * Player controller
 */
@OpenAPIDefinition(info = @Info(title = "Player position service"))
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final RegisterPlayerUseCase registerPlayerUseCase;
    private final SearchPlayersUserCase searchPlayersUserCase;

    /**
     * Register player endpoint
     * A player can be registered to a club
     * A player can be registered without a club
     * A player cannot be registered to a non-existing club
     *
     * @param requestInput {@link RegisterPlayerInput} The request input
     * @return {@link PlayerOutput} The registered player
     */
    @PostMapping
    @Operation(summary = "Register a player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerOutput registerPlayer(@RequestBody @Valid RegisterPlayerInput requestInput) throws MercatoApiException {
        return registerPlayerUseCase.execute(requestInput);
    }

    /**
     * Search players
     *
     * @param pageable Pagination parameters
     * @return {@link Page} of {@link PlayerOutput}
     */
    @GetMapping
    @Operation(summary = "Search a players")
    public Page<PlayerOutput> search(Pageable pageable) {
        return searchPlayersUserCase.execute(pageable);
    }
}
