package com.xwz.frota.api.domain.veiculo;

public class VeiculoNaoEncontradoException extends RuntimeException {
    public VeiculoNaoEncontradoException(String message) {
        super(message);
    }
}