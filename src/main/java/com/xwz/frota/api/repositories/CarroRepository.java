package com.xwz.frota.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xwz.frota.api.domain.carro.Carro;

public interface CarroRepository extends JpaRepository<Carro, UUID>{
	public Optional<Carro> findByVeiculoId(UUID veiculoId);
}
