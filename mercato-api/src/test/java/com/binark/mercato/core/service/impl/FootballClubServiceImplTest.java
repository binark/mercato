package com.binark.mercato.core.service.impl;

import com.binark.mercato.domain.entity.FootballClub;
import com.binark.mercato.infrastructure.repository.FootballClubRepository;
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
class FootballClubServiceImplTest {

    @Mock
    private FootballClubRepository repository;
    @InjectMocks
    private FootballClubServiceImpl footballClubService;

    @Test
    void save() {
        FootballClub expectedResponse = FootballClub.builder()
                                                    .id(UUID.randomUUID().toString())
                                                    .acronym("OCG NICE").birthdate(LocalDate.now())
                                                    .budget(BigDecimal.TWO).name("NICE").build();
        Mockito.when(repository.save(Mockito.any(FootballClub.class))).thenReturn(expectedResponse);
        FootballClub dataToSave = FootballClub.builder().build();
        FootballClub response = footballClubService.save(dataToSave);

        Assertions.assertThat(expectedResponse).isEqualTo(response);

        Mockito.verify(repository).save(dataToSave);
    }
}