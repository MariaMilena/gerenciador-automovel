package com.xwz.frota.infra.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xwz.frota.domain.entities.Moto;

import jakarta.transaction.Transactional;

public interface MotoRepository extends JpaRepository<Moto, UUID>{
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM motos WHERE id = :id", nativeQuery = true)
	void excluirMoto(@Param("id") UUID id);
	
	@Query(value = "SELECT * FROM motos WHERE veiculo_id = :id", nativeQuery = true)
	Optional<Moto> findByMotoId(@Param("id") UUID id);
}
