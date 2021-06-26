package com.oragetalents.controleveiculos.service;

import java.math.BigDecimal;

import com.oragetalents.controleveiculos.fipe.VeiculoAno;
import com.oragetalents.controleveiculos.fipe.VeiculoFipe;
import com.oragetalents.controleveiculos.fipe.VeiculoMarca;
import com.oragetalents.controleveiculos.fipe.VeiculoModelo;
import com.oragetalents.controleveiculos.model.entity.Veiculo;

public interface VeiculoService {
	Veiculo salvarVeiculo(Veiculo veiculo);
	
	BigDecimal buscaValor(Veiculo veiculo);
	
	VeiculoFipe  buscaFipe(String codModelo, String codMarca, String ano);
	
	VeiculoMarca[] buscaMarca();
	
	VeiculoModelo[]  buscaModelo(String codMarca);
	
	VeiculoAno [] buscaAno(String codModelo,String codMarca);
}
