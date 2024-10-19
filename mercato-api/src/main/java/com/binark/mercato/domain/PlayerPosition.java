package com.binark.mercato.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "PLAYER_POSITION")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPosition {
    @Id
    @UuidGenerator
    private String name;

    private String description;
}
