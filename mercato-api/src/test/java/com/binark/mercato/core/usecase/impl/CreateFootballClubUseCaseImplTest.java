package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.FootballClubConverter;
import com.binark.mercato.core.converter.FootballClubConverterImpl;
import com.binark.mercato.core.service.FootballClubService;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.CreateFootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CreateFootballClubUseCaseImplTest {

    private final FootballClubConverter converter = Mockito.mock(FootballClubConverterImpl.class,
                                                                 Mockito.CALLS_REAL_METHODS);
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

        CreateFootballClubOutput response = createFootballClubUseCase.execute(input);

        Assertions.assertThat(response)
                  .hasFieldOrPropertyWithValue("id", serviceResponse.getId())
                  .hasFieldOrPropertyWithValue("name", serviceResponse.getName());

        Mockito.verify(footballClubService).save(Mockito.any(FootballClub.class));
        Mockito.verify(converter).fromInputToEntity(input);
        Mockito.verify(converter).fromEntityToOutput(serviceResponse);
    }
}