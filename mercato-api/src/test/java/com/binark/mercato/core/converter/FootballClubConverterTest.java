package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.output.FootballClubOutput;
import com.binark.mercato.domain.entity.FootballClub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FootballClubConverterTest {

    FootballClubConverter footballClubConverter = new FootballClubConverterImpl();

    @Test
    void fromNullToEntity() {
        FootballClub response = footballClubConverter.fromInputToEntity(null);
        Assertions.assertThat(response).isNull();
    }

    @Test
    void fromNullToOutput() {
        FootballClubOutput response = footballClubConverter.fromEntityToOutput(null);
        Assertions.assertThat(response).isNull();
    }
}