package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.exception.MercatoApiException;

/**
 * Register player use case
 */
public interface RegisterPlayerUseCase {

    /**
     * Execute register player use case
     *
     * @param input {@link RegisterPlayerInput} The data input
     * @return {@link PlayerOutput} The registered player
     * @throws MercatoApiException If the club or position doesn't exist
     */
    PlayerOutput execute(RegisterPlayerInput input) throws MercatoApiException;
}
