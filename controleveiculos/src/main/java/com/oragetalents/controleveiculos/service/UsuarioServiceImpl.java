package com.oragetalents.controleveiculos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oragetalents.controleveiculos.exception.RegraNegocioException;
import com.oragetalents.controleveiculos.model.entity.Usuario;
import com.oragetalents.controleveiculos.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		validarCpf(usuario.getCpf());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		if(repository.existsByEmail(email))
			throw new RegraNegocioException("Já existe um usuário cadatrado para o email informado");
	}
	
	@Override
	public void validarCpf(String cpf) {
		if(repository.existsByCpf(cpf))
			throw new RegraNegocioException("Já existe um usuário cadatrado para o cpf informado");
	}

}
