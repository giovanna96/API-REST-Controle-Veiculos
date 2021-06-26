package com.oragetalents.controleveiculos.service;

import java.math.BigDecimal;
import java.util.List;

import com.oragetalents.controleveiculos.fipe.VeiculoFipe;
import com.oragetalents.controleveiculos.fipe.VeiculoMarca;
import com.oragetalents.controleveiculos.fipe.VeiculoModelo;
import com.oragetalents.controleveiculos.model.entity.Veiculo;

public interface VeiculoService {
	Veiculo salvarVeiculo(Veiculo veiculo);
	
	BigDecimal buscaValor(Veiculo veiculo);
	
	List<VeiculoFipe> buscaFipe(int codModelo, int codMarca, String ano);
	
	VeiculoMarca[] buscaMarca();
	
	VeiculoModelo[] buscaModelo(String codMarca);
}
