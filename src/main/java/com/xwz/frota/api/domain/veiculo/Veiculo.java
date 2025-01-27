package com.xwz.frota.api.domain.veiculo;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "veiculos")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
	@Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(nullable = false, length = 100)
    private String fabricante;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
}
