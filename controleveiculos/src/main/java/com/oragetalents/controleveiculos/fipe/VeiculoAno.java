package com.oragetalents.controleveiculos.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeiculoAno {
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("codigo")
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
