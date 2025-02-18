package com.xwz.frota.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carros")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
	@Id
	@GeneratedValue()
    private UUID id;

	@Column(name = "quantidade_portas", nullable = false)
    private int quantidadePortas;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustivel", nullable = false)
    private TipoCombustivel tipoCombustivel;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}
