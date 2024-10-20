package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.output.PlayerOutput;
import com.binark.mercato.domain.entity.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerConverterTest {

    PlayerConverter converter = new PlayerConverterImpl();

    @Test
    void fromNullToEntity() {
        Player player = converter.fromInputToEntity(null);
        Assertions.assertThat(player).isNull();
    }

    @Test
    void fromNullToOutput() {
        PlayerOutput output = converter.fromEntityToOutput(null);
        Assertions.assertThat(output).isNull();
    }

    @Test
    void fromNullToOutputWithoutClub() {
        PlayerOutput output = converter.fromEntityToOutputWithoutClub(null);
        Assertions.assertThat(output).isNull();
    }
}