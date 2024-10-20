package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.MockMvcTestConfiguration;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

class CreateFootballClubTest extends MockMvcTestConfiguration {

    @Test
    @SneakyThrows
    void shouldCreateFootballClub() {
        CreateFootballClubInput requestInput = CreateFootballClubInput.builder()
                                                                      .acronym("OCG NICE")
                                                                      .birthdate(LocalDate.now())
                                                                      .budget(BigDecimal.TEN)
                                                                      .name("OLYMPIQUE GYMNASTE CLUB DE NICE").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/football-clubs")
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    @SneakyThrows
    void shouldFailCreatingFootballClubWithoutName() {
        CreateFootballClubInput requestInput = CreateFootballClubInput.builder()
                                                                      .acronym("OCG NICE")
                                                                      .birthdate(LocalDate.now())
                                                                      .budget(BigDecimal.TEN)
                                                                      .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/football-clubs")
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isBadRequest())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.code").value("INVALID REQUEST INPUT"),
                             MockMvcResultMatchers.jsonPath("$.message").value("The football club name could not be " +
                                                                                       "an empty string"));
    }
}