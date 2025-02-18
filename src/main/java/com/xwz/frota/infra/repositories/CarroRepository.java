package com.xwz.frota.infra.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xwz.frota.domain.entities.Carro;

import jakarta.transaction.Transactional;

public interface CarroRepository extends JpaRepository<Carro, UUID>{
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM carros WHERE id = :id", nativeQuery = true)
	void excluirCarro(@Param("id") UUID id);
	
	@Query(value = "SELECT * FROM carros WHERE veiculo_id = :id", nativeQuery = true)
	Optional<Carro> findByCarroId(@Param("id") UUID id);
}
