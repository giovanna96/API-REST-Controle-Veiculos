package com.oragetalents.controleveiculos.service;

import java.util.List;
import java.util.Set;

import com.oragetalents.controleveiculos.fipe.VeiculoAno;
import com.oragetalents.controleveiculos.fipe.VeiculoFipe;
import com.oragetalents.controleveiculos.fipe.VeiculoMarca;
import com.oragetalents.controleveiculos.fipe.VeiculoModelo;
import com.oragetalents.controleveiculos.model.entity.Usuario;
import com.oragetalents.controleveiculos.model.entity.Veiculo;

public interface VeiculoService {
	Veiculo salvarVeiculo(Veiculo veiculo);
	
	VeiculoFipe  buscaFipe(String codModelo, String codMarca, String ano);
	
	VeiculoMarca[] buscaMarca();
	
	VeiculoModelo[]  buscaModelo(String codMarca);
	
	VeiculoAno [] buscaAno(String codModelo,String codMarca);
	
	Set<Veiculo> buscaVeiculosUsuario(Usuario usuario);
	
	
}
