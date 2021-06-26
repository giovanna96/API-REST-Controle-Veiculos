package com.oragetalents.controleveiculos.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeiculoModelo {
	@JsonProperty("Nome")
	private String nome;
	
	@JsonProperty("Codigo")
	private String codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
