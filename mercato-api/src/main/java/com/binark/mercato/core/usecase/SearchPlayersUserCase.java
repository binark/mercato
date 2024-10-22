package com.binark.mercato.core.usecase;

import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.dto.query_descriptor.PlayerQueryDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Search players use case
 * <p>I used for this search a maven package that I created</p>
 * @see <a href="https://github.com/binark/spring-query-predicate">Spring Query Predicate</a>
 */
public interface SearchPlayersUserCase {

    /**
     * Execute search players use case
     *
     * @param queryDescriptor Search query parameters
     * @param pageable Pagination parameters
     * @return {@link Page} of {@link PlayerOutput}
     */
    Page<PlayerOutput> execute(PlayerQueryDescriptor queryDescriptor, Pageable pageable);
}
