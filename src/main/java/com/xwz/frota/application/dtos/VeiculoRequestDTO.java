package com.xwz.frota.application.dtos;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
    String modelo,
    String fabricante,
    int ano,
    BigDecimal preco,
    String tipo, // "carro" ou "moto"
    Integer quantidadePortas, // Apenas para carros
    com.xwz.frota.domain.entities.TipoCombustivel tipoCombustivel, // Apenas para carros
    Integer cilindrada // Apenas para motos
) {
}
