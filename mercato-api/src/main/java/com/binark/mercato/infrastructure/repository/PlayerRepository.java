package com.binark.mercato.infrastructure.repository;

import com.binark.mercato.domain.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {

    Page<Player> findByClubId(String clubId, Pageable pageable);
}