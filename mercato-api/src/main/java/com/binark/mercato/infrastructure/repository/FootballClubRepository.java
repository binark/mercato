package com.binark.mercato.infrastructure.repository;

import com.binark.mercato.domain.entity.FootballClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballClubRepository extends JpaRepository<FootballClub, String> {
}