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

import com.xwz.frota.api.domain.carro.Carro;
import com.xwz.frota.api.domain.moto.Moto;
import com.xwz.frota.api.domain.veiculo.Veiculo;
import com.xwz.frota.api.domain.veiculo.VeiculoRequestDTO;
import com.xwz.frota.api.domain.veiculo.VeiculoResponseDTO;
import com.xwz.frota.api.repositories.CarroRepository;
import com.xwz.frota.api.repositories.MotoRepository;
import com.xwz.frota.api.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MotoRepository motoRepository;
	
	public Veiculo createVeiculo(VeiculoRequestDTO body) {
		// Criar e salvar o veículo
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(body.modelo());
        veiculo.setFabricante(body.fabricante());
        veiculo.setAno(body.ano());
        veiculo.setPreco(body.preco());

        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
		
        // Verificar o tipo e salvar nas tabelas específicas
        if ("carro".equals(body.tipo())) {
            if (body.quantidadePortas() == null || body.tipoCombustivel() == null) {
                throw new IllegalArgumentException("Quantidade de portas e tipo de combustível são obrigatórios para carros.");
            }

            Carro carro = new Carro();
            carro.setVeiculo(savedVeiculo);
            carro.setQuantidadePortas(body.quantidadePortas());
            carro.setTipoCombustivel(body.tipoCombustivel());
            carroRepository.save(carro);
        } else if ("moto".equals(body.tipo())) {
            if (body.cilindrada() == null) {
                throw new IllegalArgumentException("Cilindrada é obrigatória para motos.");
            }

            Moto moto = new Moto();
            moto.setVeiculo(savedVeiculo);
            moto.setCilindrada(body.cilindrada());
            motoRepository.save(moto);
        }

        return savedVeiculo;
	}
	
	public List<VeiculoResponseDTO> getVeiculos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Veiculo> veiculosPage = this.veiculoRepository.findAll(pageable);
        return veiculosPage
        		.map(veiculo -> new VeiculoResponseDTO(
        				veiculo.getId(), veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco()
        				)).stream().toList();
    } 
	
	public VeiculoResponseDTO getVeiculoById(UUID id) {
		Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
		return veiculoOptional
	            .map(veiculo -> {
	                VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(
	                        veiculo.getId(), veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco()
	                );
	                return responseDTO;
	            })
	            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
	}
	
	public void deleteVeiculo(UUID id) {
        // Verifica se o veículo existe
        Veiculo veiculo = veiculoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Verifica se o veículo está vinculado a um carro
        Optional<Carro> carro = carroRepository.findByVeiculoId(id);
        if (carro.isPresent()) {
            carroRepository.delete(carro.get()); // Exclui o carro relacionado
        }

        // Verifica se o veículo está vinculado a uma moto
        Optional<Moto> moto = motoRepository.findByVeiculoId(id);
        if (moto.isPresent()) {
            motoRepository.delete(moto.get()); // Exclui a moto relacionada
        }

        // Exclui o veículo
        veiculoRepository.delete(veiculo);
    }
	
	public Veiculo updateVeiculo(UUID veiculoId, VeiculoRequestDTO data) {
		Veiculo veiculo = veiculoRepository.findById(veiculoId).get();

        veiculo.setAno(data.ano());
        veiculo.setFabricante(data.fabricante());
        veiculo.setModelo(data.modelo());
        veiculo.setPreco(data.preco());

        return veiculoRepository.save(veiculo);
    }
}
