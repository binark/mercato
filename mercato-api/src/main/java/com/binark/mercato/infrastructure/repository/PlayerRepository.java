package com.binark.mercato.infrastructure.repository;

import com.binark.mercato.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}