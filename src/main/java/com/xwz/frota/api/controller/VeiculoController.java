package com.xwz.frota.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xwz.frota.api.domain.veiculo.Veiculo;
import com.xwz.frota.api.domain.veiculo.VeiculoRequestDTO;
import com.xwz.frota.api.repositories.VeiculoRepository;
import com.xwz.frota.api.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private VeiculoRepository repository;
	
	@PostMapping
	public ResponseEntity<Veiculo> create(@RequestBody VeiculoRequestDTO body){
		Veiculo newVeiculo = this.veiculoService.createVeiculo(body);
		
		repository.save(newVeiculo);
		
		return ResponseEntity.ok(newVeiculo);
	}
}
