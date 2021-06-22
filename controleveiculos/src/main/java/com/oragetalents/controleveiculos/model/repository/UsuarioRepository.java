package com.oragetalents.controleveiculos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oragetalents.controleveiculos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);
}
