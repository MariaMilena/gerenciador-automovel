package com.xwz.frota.api.service;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xwz.frota.api.domain.veiculo.Veiculo;
import com.xwz.frota.api.domain.veiculo.VeiculoRequestDTO;
import com.xwz.frota.api.domain.veiculo.VeiculoResponseDTO;
import com.xwz.frota.api.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	public Veiculo createVeiculo(VeiculoRequestDTO data) {
		Veiculo newVeiculo = new Veiculo();
		
		newVeiculo.setModelo(data.modelo());
		newVeiculo.setFabricante(data.fabricante());
		newVeiculo.setAno(data.ano());
		newVeiculo.setPreco(data.preco());
		
		repository.save(newVeiculo);
		
		return newVeiculo;
	}
	
	public List<VeiculoResponseDTO> getVeiculos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Veiculo> veiculosPage = this.repository.findAll(pageable);
        return veiculosPage
        		.map(veiculo -> new VeiculoResponseDTO(
        				veiculo.getId(), veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco()
        				)).stream().toList();
    } 
	
	public VeiculoResponseDTO getVeiculoById(UUID id) {
		Optional<Veiculo> veiculoOptional = repository.findById(id);
		return veiculoOptional
	            .map(veiculo -> {
	                VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(
	                        veiculo.getId(), veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco()
	                );
	                return responseDTO;
	            })
	            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
	}
	
	public void deleteVeiculo(UUID veiculoId){

        this.repository.delete(this.repository.findById(veiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found")));

    }
	
	public Veiculo updateVeiculo(UUID veiculoId, VeiculoRequestDTO data) {
		Veiculo veiculo = repository.findById(veiculoId).get();

        veiculo.setAno(data.ano());
        veiculo.setFabricante(data.fabricante());
        veiculo.setModelo(data.modelo());
        veiculo.setPreco(data.preco());

        return repository.save(veiculo);
    }
}
