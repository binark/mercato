package com.binark.mercato.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FOOTBALL_CLUB")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballClub {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ACRONYM", nullable = false, length = 20)
    private String acronym;

    @Column(name = "SLOGAN")
    private String slogan;

    @Column(name = "BUDGET", precision = 2, nullable = false)
    private BigDecimal budget;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;
}
