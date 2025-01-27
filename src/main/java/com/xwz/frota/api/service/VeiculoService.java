package com.xwz.frota.api.service;

import org.springframework.stereotype.Service;

import com.xwz.frota.api.domain.veiculo.Veiculo;
import com.xwz.frota.api.domain.veiculo.VeiculoRequestDTO;

@Service
public class VeiculoService {
	public Veiculo createVeiculo(VeiculoRequestDTO data) {
		Veiculo newVeiculo = new Veiculo();
		
		newVeiculo.setModelo(data.modelo());
		newVeiculo.setFabricante(data.fabricante());
		newVeiculo.setAno(data.ano());
		newVeiculo.setPreco(data.preco());
		
		return newVeiculo;
	}
}
