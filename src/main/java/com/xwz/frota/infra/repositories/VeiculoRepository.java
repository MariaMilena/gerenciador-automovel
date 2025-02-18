package com.xwz.frota.infra.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xwz.frota.domain.entities.Veiculo;

import jakarta.transaction.Transactional;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID>{
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM veiculos WHERE id = :id", nativeQuery = true)
	void excluirVeiculo(@Param("id") UUID id);

}
