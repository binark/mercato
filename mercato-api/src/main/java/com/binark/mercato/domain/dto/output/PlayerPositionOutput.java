package com.binark.mercato.domain.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPositionOutput {
    @Schema(description = "The position name", example = "STRICKER")
    private String name;
}
