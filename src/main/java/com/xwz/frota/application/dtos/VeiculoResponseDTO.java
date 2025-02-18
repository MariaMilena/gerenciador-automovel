package com.xwz.frota.application.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculoResponseDTO(UUID id, String modelo, String fabricante, int ano, BigDecimal preco) {
}
