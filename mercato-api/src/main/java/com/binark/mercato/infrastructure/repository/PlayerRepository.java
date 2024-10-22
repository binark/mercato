package com.binark.mercato.infrastructure.repository;

import com.binark.mercato.domain.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<Player, String>, JpaSpecificationExecutor<Player> {

    Page<Player> findByClubId(String clubId, Pageable pageable);
}