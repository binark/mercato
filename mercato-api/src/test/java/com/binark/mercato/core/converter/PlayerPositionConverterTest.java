package com.binark.mercato.core.converter;

import com.binark.mercato.domain.dto.output.PlayerPositionOutput;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerPositionConverterTest {

    PlayerPositionConverter converter = new PlayerPositionConverterImpl();

    @Test
    void fromNullToOutput() {
        PlayerPositionOutput playerPositionOutput = converter.fromEntityToOutput(null);
        Assertions.assertThat(playerPositionOutput).isNull();
    }
}