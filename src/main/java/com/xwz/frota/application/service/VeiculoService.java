package com.xwz.frota.application.service;

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

import com.xwz.frota.application.dtos.VeiculoRequestDTO;
import com.xwz.frota.application.dtos.VeiculoResponseDTO;
import com.xwz.frota.domain.entities.Carro;
import com.xwz.frota.domain.entities.Moto;
import com.xwz.frota.domain.entities.Veiculo;
import com.xwz.frota.infra.repositories.CarroRepository;
import com.xwz.frota.infra.repositories.MotoRepository;
import com.xwz.frota.infra.repositories.VeiculoRepository;

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
        Optional<Carro> carro = carroRepository.findByCarroId(id);
        if (carro.isPresent()) {
            carroRepository.excluirCarro(carro.get().getId()); // Exclui o carro relacionado
        }

        // Verifica se o veículo está vinculado a uma moto
        Optional<Moto> moto = motoRepository.findByMotoId(id);
        if (moto.isPresent()) {
            motoRepository.excluirMoto(moto.get().getId()); // Exclui a moto relacionada
        }

        // Exclui o veículo
        veiculoRepository.excluirVeiculo(veiculo.getId());
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
