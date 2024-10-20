package com.binark.mercato.infrastructure.repository;

import com.binark.mercato.domain.entity.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, String> {
}