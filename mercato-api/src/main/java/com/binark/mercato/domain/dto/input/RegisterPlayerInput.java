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
public class RegisterPlayerInput {

    @NotBlank(message = "The player lastname could not be an empty string")
    @Size(min = 3, max = 100, message = "The player lastname should have at least 3 characters and at most 100")
    @Schema(description = "The player lastname", requiredMode = Schema.RequiredMode.REQUIRED, example = "ETOO",
            minLength = 3, maxLength = 100)
    private String lastname;

    @Schema(description = "The player firstname", example = "SAMUEL")
    private String firstname;

    @NotBlank(message = "The player nickname could not be an empty string")
    @Size(min = 3, max = 100, message = "The player nickname should have at least 3 characters and at most 100")
    @Schema(description = "The player nickname", example = "ETOO", requiredMode =
            Schema.RequiredMode.REQUIRED, minLength = 3, maxLength = 100)
    private String nickName;

    @Positive(message = "The player jersey number should be greater than 0")
    @Schema(description = "The player jersey number, should be positive", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "9")
    private Integer jerseyNumber;

    @NotNull(message = "The salary could not be null")
    @Positive(message = "The salary should be great than 0")
    @Schema(description = "The player salary, should be positive", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal salary;

    @Schema(description = "The player position", example = "STRIKER")
    private String position;

    @Schema(description = "The player birthdate", example = "2024-19-10")
    private LocalDate birthdate;

    @Schema(description = "The player registration date to the club", example = "2024-19-10")
    private LocalDate registrationDate;

    @Schema(description = "The club id that is registering the player")
    private String club;
}
