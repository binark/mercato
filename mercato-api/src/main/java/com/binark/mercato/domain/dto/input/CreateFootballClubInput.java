package com.binark.mercato.domain.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFootballClubInput {

    @NotBlank(message = "The football club name could not be an empty string")
    @Size(min = 3, max = 100, message = "The football club name should have at least 3 characters and at most 100")
    @Schema(description = "The football club name", requiredMode = Schema.RequiredMode.REQUIRED, example = "OLYMPIQUE" +
            " GYMNASTE CLUB DE NICE", minLength = 3, maxLength = 100)
    private String name;

    @NotBlank(message = "The football club acronym could not be an empty string")
    @Size(min = 3, max = 20, message = "The football club acronym should have at least 3 characters and at most 20")
    @Schema(description = "The football club acronym", example = "OCG NICE", requiredMode =
            Schema.RequiredMode.REQUIRED, minLength = 3, maxLength = 20)
    private String acronym;

    @Schema(description = "The football club slogan")
    private String slogan;

    @NotNull(message = "The budget could not be null")
    @Positive(message = "The budget should be great than 0")
    @Schema(description = "The football club budget, should be positive", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal budget;

    @Schema(description = "The football club birthdate", example = "2024-19-10")
    private LocalDate birthdate;
}
