package com.binark.mercato.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PLAYER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "LASTNAME", nullable = false, length = 100)
    private String lastname;

    private String firstname;

    @Column(name = "PLAYER_NAME", nullable = false, length = 100)
    private String playerName;

    @Column(name = "JERSEY_NUMBER", nullable = false)
    private Integer jerseyNumber;

    @Column(name = "SALARY", nullable = false, precision = 2)
    private BigDecimal salary;

    @ManyToOne
    private PlayerPosition position;

    private LocalDate birthdate;

    private LocalDate registrationDate;

    @ManyToOne
    private FootballClub club;
}
