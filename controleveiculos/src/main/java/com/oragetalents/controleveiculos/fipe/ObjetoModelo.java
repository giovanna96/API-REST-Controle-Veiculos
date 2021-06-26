package com.oragetalents.controleveiculos.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjetoModelo {
	@JsonProperty("anos")
	private VeiculoAno[] ano;
	
	@JsonProperty("modelos")
	private VeiculoModelo[] modelo;

	public VeiculoAno[] getAno() {
		return ano;
	}

	public void setAno(VeiculoAno[] ano) {
		this.ano = ano;
	}

	public VeiculoModelo[] getModelo() {
		return modelo;
	}

	public void setModelo(VeiculoModelo[] modelo) {
		this.modelo = modelo;
	}
	
	
}
