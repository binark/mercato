package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerConverter;
import com.binark.mercato.core.service.PlayerService;
import com.binark.mercato.core.usecase.SearchPlayersUserCase;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchPlayersUserCaseImpl implements SearchPlayersUserCase {

    private final PlayerService playerService;
    private final PlayerConverter converter;

    @Override
    public Page<PlayerOutput> execute(Pageable pageable) {
        Page<Player> players = playerService.find(pageable);
        return players.map(converter::fromEntityToOutput);
    }
}
