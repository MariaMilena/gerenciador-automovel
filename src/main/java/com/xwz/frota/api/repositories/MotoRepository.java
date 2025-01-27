package com.xwz.frota.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xwz.frota.api.domain.moto.Moto;

public interface MotoRepository extends JpaRepository<Moto, UUID>{
	public Optional<Moto> findByVeiculoId(UUID veiculoId);
}
