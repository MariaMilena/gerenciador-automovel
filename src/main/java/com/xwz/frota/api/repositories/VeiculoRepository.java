package com.xwz.frota.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xwz.frota.api.domain.veiculo.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID>{
}
