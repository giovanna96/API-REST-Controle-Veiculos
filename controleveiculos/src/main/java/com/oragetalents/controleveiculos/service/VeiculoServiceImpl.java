package com.oragetalents.controleveiculos.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oragetalents.controleveiculos.exception.RegraNegocioException;
import com.oragetalents.controleveiculos.fipe.VeiculoFipe;
import com.oragetalents.controleveiculos.fipe.VeiculoMarca;
import com.oragetalents.controleveiculos.fipe.VeiculoModelo;
import com.oragetalents.controleveiculos.model.entity.Veiculo;
import com.oragetalents.controleveiculos.model.repository.VeiculoRepository;

@Service
public class VeiculoServiceImpl implements VeiculoService{
	
	private VeiculoRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public VeiculoServiceImpl(RestTemplate restTemplate, VeiculoRepository repository) {
		this.restTemplate = restTemplate;
		this.repository = repository;
	}

	@Override
	public Veiculo salvarVeiculo(Veiculo veiculo) {
		VeiculoMarca[] veiculosMarcas = buscaMarca();
		VeiculoMarca marca = (VeiculoMarca) Arrays.stream(veiculosMarcas)
							.filter(veiculoMarca -> veiculo.getMarca().equals(veiculoMarca.getNome()))
							.findAny()
							.orElse(null);
		if(marca == null)
			throw new RegraNegocioException("Não foi possível encontrar as informações de veículo para a marca informada");
							
		VeiculoModelo[] veiculosModelos = buscaModelo(marca.getCodigo());
		VeiculoModelo modelo = (VeiculoModelo) Arrays.stream(veiculosModelos)
							.filter(veiculoModelo -> veiculo.getModelo().equals(veiculoModelo.getNome()))
							.findAny()
							.orElse(null);
		if(modelo == null)
			throw new RegraNegocioException("Não foi possível encontrar as informações de veículo para o modelo informado");
		veiculo.setMarca(marca.getNome());
		veiculo.setValor(new BigDecimal("10"));
	
		
		return repository.save(veiculo);
	}

	@Override
	public BigDecimal buscaValor(Veiculo veiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VeiculoFipe> buscaFipe(int codModelo, int codMarca, String ano) {
		List<VeiculoFipe> veiculosValores = (List<VeiculoFipe>) this.restTemplate.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3", VeiculoFipe.class);
		return null;
	}

	@Override
	public VeiculoMarca[] buscaMarca() {
		ResponseEntity<VeiculoMarca[]> responseEntity =this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas", VeiculoMarca[].class);
		VeiculoMarca[] veiculosMarcas = responseEntity.getBody();
		return veiculosMarcas;
	}

	@Override
	public VeiculoModelo[] buscaModelo(String codMarca) {
		ResponseEntity<VeiculoModelo[]> responseEntity =this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codMarca+"modelos", VeiculoModelo[].class);
		VeiculoModelo[] veiculosModelos = responseEntity.getBody();
		return veiculosModelos;
	}
	

}
