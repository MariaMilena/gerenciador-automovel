package com.xwz.frota.api.domain.tipoCombustivel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCombustivel {
    ETANOL, DIESEL, GASOLINA, FLEX;

    @JsonCreator
    public static TipoCombustivel fromString(String value) {
        return TipoCombustivel.valueOf(value.toUpperCase()); // Converte para maiúsculas
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}
