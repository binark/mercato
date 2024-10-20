package com.binark.mercato.core.usecase.impl;

import com.binark.mercato.core.converter.FootballClubConverterImpl;
import com.binark.mercato.core.service.impl.FootballClubServiceImpl;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SearchFootballClubUseCaseImplTest {

    @Mock
    private FootballClubServiceImpl footballClubService;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private FootballClubConverterImpl converter;
    @InjectMocks
    private SearchFootballClubUseCaseImpl searchFootballClubUseCase;

    @Test
    void execute() {
        List<FootballClub> footballClubs = List.of(FootballClub.builder().id(UUID.randomUUID().toString()).build());
        Page<FootballClub> serviceResponse = new PageImpl<>(footballClubs);
        Mockito.when(footballClubService.search(Mockito.any(Pageable.class))).thenReturn(serviceResponse);
        Pageable pageable = Pageable.ofSize(10);
        Page<FootballClubOutput> response = searchFootballClubUseCase.execute(pageable);

        Assertions.assertThat(response.getTotalElements()).isEqualTo(serviceResponse.getTotalElements());
        Assertions.assertThat(response.getTotalPages()).isEqualTo(serviceResponse.getTotalPages());
        Assertions.assertThat(response.getNumberOfElements()).isEqualTo(serviceResponse.getNumberOfElements());
        Assertions.assertThat(response.getContent()).hasSameSizeAs(serviceResponse.getContent());
        Assertions.assertThat(response.getContent().stream().map(FootballClubOutput::getId).toList())
                  .usingRecursiveAssertion()
                  .isEqualTo(serviceResponse.getContent().stream().map(FootballClub::getId).toList());

        Mockito.verify(footballClubService).search(pageable);
        Mockito.verify(converter, Mockito.times(footballClubs.size())).fromEntityToOutput(Mockito.any(FootballClub.class));
    }
}