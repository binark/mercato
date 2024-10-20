package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.MockMvcTestConfiguration;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class FindPlayerPositionTest extends MockMvcTestConfiguration {

    public static final String PATH = "/player-positions";

    @Test
    @SneakyThrows
    void shouldReturnAllPlayerPositions() {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.*").isArray(),
                             MockMvcResultMatchers.jsonPath("$.*")
                                                  .value(Matchers.hasSize(Matchers.greaterThan(0))),
                             MockMvcResultMatchers.jsonPath("$.[*].name")
                                                  .value(Matchers.hasItem("STRIKER")));
    }
}