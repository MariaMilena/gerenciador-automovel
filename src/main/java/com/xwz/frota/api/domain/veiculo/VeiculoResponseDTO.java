package com.xwz.frota.api.domain.veiculo;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculoResponseDTO(UUID id, String modelo, String fabricante, int ano, BigDecimal preco) {
}
