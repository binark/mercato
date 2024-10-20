package com.binark.mercato.domain.entity;

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

    @Column(name = "FIRSTNAME", length = 100)
    private String firstname;

    @Column(name = "PLAYER_NAME", nullable = false, length = 100)
    private String playerName;

    @Column(name = "JERSEY_NUMBER", nullable = false)
    private Integer jerseyNumber;

    @Column(name = "SALARY", nullable = false, precision = 2)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "POSITION")
    private PlayerPosition position;

    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;

    @Column(name = "REGISTRATION_DATE")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "FOOTBALL_CLUB")
    private FootballClub club;
}
