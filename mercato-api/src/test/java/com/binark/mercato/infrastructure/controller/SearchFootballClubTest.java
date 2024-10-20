package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.MockMvcTestConfiguration;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

class SearchFootballClubTest extends MockMvcTestConfiguration {

    public static final String PATH = "/football-clubs";

    @Test
    @SneakyThrows
    void shouldReturnAllFootballClubs() {
        CreateFootballClubInput requestInput = CreateFootballClubInput.builder()
                                                                      .acronym("OCG NICE")
                                                                      .birthdate(LocalDate.now())
                                                                      .budget(BigDecimal.TEN)
                                                                      .name("OLYMPIQUE GYMNASTE CLUB DE NICE").build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                                                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                 .content(mapper.writeValueAsString(requestInput)))
                                  .andDo(MockMvcResultHandlers.print())
                                  .andExpect(MockMvcResultMatchers.status().isCreated())
                                  .andReturn();

        String content = result.getResponse().getContentAsString();
        FootballClubOutput createdFootballClub = mapper.readValue(content, FootballClubOutput.class);
        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.content").isArray(),
                             MockMvcResultMatchers.jsonPath("$.content")
                                                  .value(Matchers.hasSize(Matchers.greaterThan(0))),
                             MockMvcResultMatchers.jsonPath("$.content[*].id")
                                                  .value(Matchers.hasItem(createdFootballClub.getId())));
    }

    @Test
    @SneakyThrows
    void shouldReturnPagedFootballClubs() {
        CreateFootballClubInput requestInput = CreateFootballClubInput.builder()
                                                                      .acronym("OCG NICE")
                                                                      .birthdate(LocalDate.now())
                                                                      .budget(BigDecimal.TEN)
                                                                      .name("OLYMPIQUE GYMNASTE CLUB DE NICE").build();

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "?size=1"))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.content").isArray(),
                             MockMvcResultMatchers.jsonPath("$.content")
                                                  .value(Matchers.hasSize(1)));
    }
}