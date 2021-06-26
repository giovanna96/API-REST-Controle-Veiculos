package com.oragetalents.controleveiculos.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oragetalents.controleveiculos.exception.RegraNegocioException;
import com.oragetalents.controleveiculos.fipe.ObjetoModelo;
import com.oragetalents.controleveiculos.fipe.VeiculoAno;
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
							
		VeiculoModelo[] veiculoModelos = buscaModelo(marca.getCodigo());
		VeiculoModelo modelo = (VeiculoModelo) Arrays.stream(veiculoModelos)
							.filter(veiculoModelo -> veiculo.getModelo().equals(veiculoModelo.getNome()))
							.findAny()
							.orElse(null);
		if(modelo == null)
			throw new RegraNegocioException("Não foi possível encontrar as informações de veículo para o modelo informado");
		
		VeiculoAno[] veiculoAnos = buscaAno(modelo.getCodigo(), marca.getCodigo());
		VeiculoAno ano = (VeiculoAno) Arrays.stream(veiculoAnos)
				.filter(veiculoAno -> veiculo.getAno().toString().equals(veiculoAno.getNome().substring(0, veiculoAno.getNome().indexOf(" "))))
				.findAny()
				.orElse(null);
		
		if(ano == null)
			throw new RegraNegocioException("Não foi possível encontrar as informações de veículo para o ano informado");
		
		VeiculoFipe veiculoFipe = buscaFipe(modelo.getCodigo(), marca.getCodigo(),ano.getCodigo().toString());

		
		veiculo.setMarca(veiculoFipe.getMarca());
		veiculo.setModelo(veiculoFipe.getModelo());
		veiculo.setAno(veiculoFipe.getAnoModelo());
		veiculo.setValor(veiculoFipe.getValor());
	 
		
		return repository.save(veiculo);
	}

	@Override
	public BigDecimal buscaValor(Veiculo veiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VeiculoFipe buscaFipe(String codModelo, String codMarca, String ano) {
		ResponseEntity<VeiculoFipe> responseEntity = this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codMarca+"/modelos/"+codModelo+"/anos/"+ano, VeiculoFipe.class);
		VeiculoFipe veiculo = responseEntity.getBody();
		return veiculo;
	}

	@Override
	public VeiculoMarca[] buscaMarca() {
		ResponseEntity<VeiculoMarca[]> responseEntity =this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas", VeiculoMarca[].class);
		VeiculoMarca[] veiculosMarcas = responseEntity.getBody();
		return veiculosMarcas;
	}

	@Override
	public VeiculoModelo[]  buscaModelo(String codMarca) {
		ResponseEntity<ObjetoModelo> responseEntity =this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codMarca+"/modelos", ObjetoModelo.class);
		ObjetoModelo objetoModelos = responseEntity.getBody();
		VeiculoModelo[] veiculoModelos = objetoModelos.getModelo();
		return veiculoModelos;
	}

	@Override
	public VeiculoAno[] buscaAno(String codModelo, String codMarca) {
		ResponseEntity<VeiculoAno[]> responseEntity =this.restTemplate.getForEntity("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codMarca+"/modelos/"+codModelo+"/anos", VeiculoAno[].class);
		VeiculoAno[] veiculoAnos = responseEntity.getBody();
		return veiculoAnos;
	}
	
	
	

}
