package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.PlayerConverterImpl;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.core.service.PlayerPositionService;
import com.binark.mercato.core.service.PlayerService;
import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.entity.FootballClub;
import com.binark.mercato.domain.entity.Player;
import com.binark.mercato.domain.entity.PlayerPosition;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class RegisterPlayerUseCaseImplTest {

    @Mock
    private PlayerService playerService;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private PlayerConverterImpl converter;
    @Mock
    private FootballClubService footballClubService;
    @Mock
    private PlayerPositionService playerPositionService;
    @InjectMocks
    private RegisterPlayerUseCaseImpl registerPlayerUseCase;

    @Test
    @SneakyThrows
    void executeNormalScenario() {
        FootballClub club = FootballClub.builder().id(UUID.randomUUID().toString()).acronym("FCB").build();
        Mockito.when(footballClubService.getClub(Mockito.anyString())).thenReturn(club);
        PlayerPosition position = PlayerPosition.builder().name("FOO").build();
        Mockito.when(playerPositionService.getPosition(Mockito.anyString())).thenReturn(position);

        RegisterPlayerInput input = buildInput();

        PlayerOutput response = registerPlayerUseCase.execute(input);

        Assertions.assertThat(response)
                  .isNotNull()
                  .hasFieldOrPropertyWithValue("lastname", input.getLastname())
                  .hasFieldOrPropertyWithValue("jerseyNumber", input.getJerseyNumber())
                  .hasFieldOrPropertyWithValue("firstname", input.getFirstname());

        Assertions.assertThat(response)
                  .extracting(PlayerOutput::getClub)
                  .hasFieldOrPropertyWithValue("acronym", club.getAcronym());

        Assertions.assertThat(response)
                  .extracting(PlayerOutput::getPosition)
                  .isEqualTo(position.getName());

        Mockito.verify(footballClubService).getClub(input.getClub());
        Mockito.verify(playerPositionService).getPosition(input.getPosition());
        Mockito.verify(converter).fromEntityToOutput(Mockito.any(Player.class));
        Mockito.verify(converter).fromInputToEntity(input);
    }

    private RegisterPlayerInput buildInput() {
        return RegisterPlayerInput.builder()
                                  .lastname("ETOO")
                                  .jerseyNumber(9)
                                  .firstname("SAMUEL")
                                  .registrationDate(LocalDate.now())
                                  .salary(BigDecimal.TEN).nickName("ETOO")
                                  .birthdate(LocalDate.now())
                                  .club(UUID.randomUUID().toString())
                                  .position("BAR")
                                  .build();
    }
}