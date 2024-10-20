package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.output.PlayerPositionOutput;

import java.util.Set;

/**
 * Get all player positions use case
 */
public interface FindPlayerPositionUseCase {

    /**
     * Get all player position use case execution
     *
     * @return {@link Set} of {@link PlayerPositionOutput}
     */
    Set<PlayerPositionOutput> execute();
}
