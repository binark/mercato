package com.binark.mercato.domain.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerOutput {

    @Schema(description = "The player identifier")
    private String id;

    @Schema(description = "The player lastname", example = "ETOO")
    private String lastname;

    @Schema(description = "The player firstname", example = "SAMUEL")
    private String firstname;

    @Schema(description = "The player nickname", example = "ETOO")
    private String nickName;

    @Schema(description = "The player jersey number", example = "9")
    private int jerseyNumber;

    @Schema(description = "The player salary")
    private BigDecimal salary;

    @Schema(description = "The player position", example = "STRIKER")
    private String position;

    @Schema(description = "The player birthdate", example = "2024-19-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Schema(description = "The player registration date to the club", example = "2024-19-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @Schema(description = "The player club")
    private FootballClubOutput club;
}
