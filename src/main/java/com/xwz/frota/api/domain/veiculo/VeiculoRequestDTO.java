package com.xwz.frota.api.domain.veiculo;

import java.math.BigDecimal;

public record VeiculoRequestDTO(String modelo, String fabricante, int ano, BigDecimal preco) {
}
