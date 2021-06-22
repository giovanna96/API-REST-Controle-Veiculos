package com.oragetalents.controleveiculos.service;

import com.oragetalents.controleveiculos.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	void validarCpf(String cpf);
}
