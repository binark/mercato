package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.output.PlayerOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Search players use case
 */
public interface SearchPlayersUserCase {

    /**
     * Execute search players use case
     *
     * @param pageable Pagination parameters
     * @return {@link Page} of {@link PlayerOutput}
     */
    Page<PlayerOutput> execute(Pageable pageable);
}
