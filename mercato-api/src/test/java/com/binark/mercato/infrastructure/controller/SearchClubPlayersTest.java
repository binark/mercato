package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.MockMvcTestConfiguration;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

class SearchClubPlayersTest extends MockMvcTestConfiguration {

    public static final String PLAYERS_PATH = "/players";
    public static final String LASTNAME = "ETOO";
    public static final String FIRSTNAME = "SAMUEL";
    public static final int JERSEY_NUMBER = 9;
    public static final String POSITION = "STRIKER";
    public static final LocalDate BIRTHDATE = LocalDate.now();
    public static final String ACRONYM = "FCB";
    public static final String FOOTBALL_CLUB_NAME = "BARCELONA FOOTBALL CLUB";
    public static final String FOOTBALL_CLUB_PATH = "/football-clubs";

    @Test
    @SneakyThrows
    void shouldReturnPlayersForAClub() {
        CreateFootballClubInput clubInput = CreateFootballClubInput.builder()
                                                                   .acronym(ACRONYM)
                                                                   .birthdate(BIRTHDATE)
                                                                   .budget(BigDecimal.TEN)
                                                                   .name(FOOTBALL_CLUB_NAME)
                                                                   .build();

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post(FOOTBALL_CLUB_PATH)
                                                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                      .content(mapper.writeValueAsString(clubInput)))
                                       .andDo(MockMvcResultHandlers.print())
                                       .andExpect(MockMvcResultMatchers.status().isCreated())
                                       .andReturn()
                                       .getResponse()
                                       .getContentAsString();

        FootballClubOutput createdFootballClub = mapper.readValue(responseString, FootballClubOutput.class);

        RegisterPlayerInput requestInput = RegisterPlayerInput.builder()
                                                              .lastname(LASTNAME)
                                                              .firstname(FIRSTNAME)
                                                              .jerseyNumber(JERSEY_NUMBER)
                                                              .position(POSITION)
                                                              .birthdate(BIRTHDATE)
                                                              .salary(BigDecimal.TEN)
                                                              .nickName(LASTNAME)
                                                              .club(createdFootballClub.getId())
                                                              .build();

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated());

        requestInput.setClub(null);
        requestInput.setPosition(null);

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get(FOOTBALL_CLUB_PATH + "/" + createdFootballClub.getId() + "/players"))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.content").isArray(),
                             MockMvcResultMatchers.jsonPath("$.content")
                                                  .value(Matchers.hasSize(1)),
                             MockMvcResultMatchers.jsonPath("$.content[0].position")
                                                  .value("STRIKER"));
    }
}