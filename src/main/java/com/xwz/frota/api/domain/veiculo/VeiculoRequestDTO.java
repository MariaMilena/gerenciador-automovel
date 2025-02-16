package com.xwz.frota.api.domain.veiculo;

import java.math.BigDecimal;

import com.xwz.frota.api.domain.tipoCombustivel.TipoCombustivel;

public record VeiculoRequestDTO(
    String modelo,
    String fabricante,
    int ano,
    BigDecimal preco,
    String tipo, // "carro" ou "moto"
    Integer quantidadePortas, // Apenas para carros
    TipoCombustivel tipoCombustivel, // Apenas para carros
    Integer cilindrada // Apenas para motos
) {
}
