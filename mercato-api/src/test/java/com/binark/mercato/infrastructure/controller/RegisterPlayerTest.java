package com.binark.mercato.infrastructure.controller;

import com.binark.mercato.MockMvcTestConfiguration;
import com.binark.mercato.domain.dto.input.CreateFootballClubInput;
import com.binark.mercato.domain.dto.input.RegisterPlayerInput;
import com.binark.mercato.domain.dto.output.FootballClubOutput;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class RegisterPlayerTest extends MockMvcTestConfiguration {

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
    void shouldRegisterPlayerToAClub() {
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
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty(),
                             MockMvcResultMatchers.jsonPath("$.lastname").value(LASTNAME),
                             MockMvcResultMatchers.jsonPath("$.club.acronym").value(ACRONYM));
    }

    @Test
    @SneakyThrows
    void shouldFailRegisteringPlayerWithNonExistingPosition() {
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
                                                              .position("FOO")
                                                              .birthdate(BIRTHDATE)
                                                              .salary(BigDecimal.TEN)
                                                              .nickName(LASTNAME)
                                                              .club(createdFootballClub.getId())
                                                              .build();

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldFailRegisteringPlayerToNonExistingClub() {
        RegisterPlayerInput requestInput = RegisterPlayerInput.builder()
                                                              .lastname(LASTNAME)
                                                              .firstname(FIRSTNAME)
                                                              .jerseyNumber(JERSEY_NUMBER)
                                                              .position(POSITION)
                                                              .birthdate(BIRTHDATE)
                                                              .salary(BigDecimal.TEN)
                                                              .nickName(LASTNAME)
                                                              .club(UUID.randomUUID().toString())
                                                              .build();

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldRegisterPlayerWithoutClub() {

        RegisterPlayerInput requestInput = RegisterPlayerInput.builder()
                                                              .lastname(LASTNAME)
                                                              .firstname(FIRSTNAME)
                                                              .jerseyNumber(JERSEY_NUMBER)
                                                              .position(POSITION)
                                                              .birthdate(BIRTHDATE)
                                                              .salary(BigDecimal.TEN)
                                                              .nickName(LASTNAME)
                                                              .build();

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty(),
                             MockMvcResultMatchers.jsonPath("$.lastname").value(LASTNAME),
                             MockMvcResultMatchers.jsonPath("$.club").isEmpty());
    }

    @Test
    @SneakyThrows
    void shouldRegisterPlayerWithoutPosition() {
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
                                                              .birthdate(BIRTHDATE)
                                                              .salary(BigDecimal.TEN)
                                                              .nickName(LASTNAME)
                                                              .club(createdFootballClub.getId())
                                                              .build();

        mockMvc.perform(MockMvcRequestBuilders.post(PLAYERS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(mapper.writeValueAsString(requestInput)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpectAll(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty(),
                             MockMvcResultMatchers.jsonPath("$.lastname").value(LASTNAME),
                             MockMvcResultMatchers.jsonPath("$.position").isEmpty());
    }
}