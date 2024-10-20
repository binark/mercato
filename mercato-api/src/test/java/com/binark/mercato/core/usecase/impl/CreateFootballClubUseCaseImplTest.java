package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.FootballClubConverterImpl;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
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
class CreateFootballClubUseCaseImplTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private FootballClubConverterImpl converter;
    @Mock
    private FootballClubService footballClubService;
    @InjectMocks
    private CreateFootballClubUseCaseImpl createFootballClubUseCase;

    @Test
    void execute() {
        FootballClub serviceResponse = FootballClub.builder()
                                                   .id(UUID.randomUUID().toString())
                                                   .acronym("OCG NICE").birthdate(LocalDate.now())
                                                   .budget(BigDecimal.TWO).name("NICE").build();
        Mockito.when(footballClubService.save(Mockito.any(FootballClub.class))).thenReturn(serviceResponse);

        CreateFootballClubInput input = CreateFootballClubInput.builder().build();

        FootballClubOutput response = createFootballClubUseCase.execute(input);

        Assertions.assertThat(response)
                  .hasFieldOrPropertyWithValue("id", serviceResponse.getId())
                  .hasFieldOrPropertyWithValue("name", serviceResponse.getName());

        Mockito.verify(footballClubService).save(Mockito.any(FootballClub.class));
        Mockito.verify(converter).fromInputToEntity(input);
        Mockito.verify(converter).fromEntityToOutput(serviceResponse);
    }
}