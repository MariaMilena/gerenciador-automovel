package com.xwz.frota.api.domain.moto;

import java.util.UUID;

import com.xwz.frota.api.domain.veiculo.Veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "motos")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Moto {
	@Id
	@GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private int cilindrada;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}
