package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerConverter;
import com.binark.mercato.core.service.PlayerService;
import com.binark.mercato.core.usecase.SearchPlayersUserCase;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.dto.query_descriptor.PlayerQueryDescriptor;
import com.binark.mercato.domain.entity.Player;
import io.github.binark.querypredicate.descriptor.converter.QueryDescriptorConverter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchPlayersUserCaseImpl implements SearchPlayersUserCase {

    private final PlayerService playerService;
    private final PlayerConverter converter;
    private final QueryDescriptorConverter<PlayerQueryDescriptor> queryDescriptorConverter;

    @Override
    public Page<PlayerOutput> execute(PlayerQueryDescriptor queryDescriptor, Pageable pageable) {
        Specification<Player> specification = specificationBuilder(queryDescriptor);
        Page<Player> players = playerService.find(specification, pageable);
        return players.map(converter::fromEntityToOutput);
    }

    private Specification<Player> specificationBuilder(PlayerQueryDescriptor queryDescriptor) {
        return (root, query, builder) -> {
            List<Predicate> predicates = queryDescriptorConverter.convert(root, builder, queryDescriptor);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
