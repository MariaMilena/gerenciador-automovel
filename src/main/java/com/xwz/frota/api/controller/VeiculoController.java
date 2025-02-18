package com.xwz.frota.api.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xwz.frota.api.exceptions.VeiculoNaoEncontradoException;
import com.xwz.frota.application.dtos.VeiculoRequestDTO;
import com.xwz.frota.application.dtos.VeiculoResponseDTO;
import com.xwz.frota.application.service.VeiculoService;
import com.xwz.frota.domain.entities.Veiculo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/veiculo")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
	
	@PostMapping
	public ResponseEntity<Veiculo> create(@RequestBody VeiculoRequestDTO body){
		Veiculo newVeiculo = this.veiculoService.createVeiculo(body);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newVeiculo);
	}
	
    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getVeiculos(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size) {
        List<VeiculoResponseDTO> allVeiculos = this.veiculoService.getVeiculos(page, size);
        return ResponseEntity.ok(allVeiculos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getVeiculoById(@PathVariable UUID id) {
    	try {
    		VeiculoResponseDTO veiculo =  veiculoService.getVeiculoById(id);
            return ResponseEntity.ok(veiculo);
        } catch (VeiculoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable UUID id, @RequestBody VeiculoRequestDTO veiculoDTO) {
    	try {
    		Veiculo veiculo = veiculoService.updateVeiculo(id, veiculoDTO);
            return ResponseEntity.ok(veiculo);
        } catch (VeiculoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{veiculoId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID veiculoId) {
    	try {
    		veiculoService.deleteVeiculo(veiculoId);
            return ResponseEntity.noContent().build();
        } catch (VeiculoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
