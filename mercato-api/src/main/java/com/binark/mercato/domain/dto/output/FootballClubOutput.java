package com.binark.mercato.domain.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballClubOutput {
    @Schema(description = "The football club identifier")
    private String id;

    @Schema(description = "The football club name", example = "OLYMPIQUE GYMNASTE CLUB DE NICE")
    private String name;

    @Schema(description = "The football club acronym", example = "OCG NICE")
    private String acronym;

    @Schema(description = "The football club slogan")
    private String slogan;

    @Schema(description = "The football club budget")
    private BigDecimal budget;

    @Schema(description = "The football club birthdate", example = "2024-19-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}
