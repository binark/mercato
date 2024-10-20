package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerConverter;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.core.service.PlayerPositionService;
import com.binark.mercato.core.service.PlayerService;
import com.binark.mercato.core.usecase.RegisterPlayerUseCase;
import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.entity.FootballClub;
import com.binark.mercato.domain.entity.Player;
import com.binark.mercato.domain.entity.PlayerPosition;
import com.binark.mercato.exception.MercatoApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RegisterPlayerUseCaseImpl implements RegisterPlayerUseCase {

    private final PlayerService playerService;
    private final PlayerConverter converter;
    private final FootballClubService footballClubService;
    private final PlayerPositionService playerPositionService;

    @Override
    @Transactional
    public PlayerOutput execute(RegisterPlayerInput input) throws MercatoApiException {
        FootballClub club = null;
        String clubId = input.getClub();
        if (clubId != null) {
            club = footballClubService.getClub(clubId);
        }
        PlayerPosition position = null;
        String positionName = input.getPosition();
        if (positionName != null) {
            position = playerPositionService.getPosition(input.getPosition());
        }
        Player player = converter.fromInputToEntity(input);
        player.setClub(club);
        player.setPosition(position);
        playerService.save(player);
        return converter.fromEntityToOutput(player);
    }
}
