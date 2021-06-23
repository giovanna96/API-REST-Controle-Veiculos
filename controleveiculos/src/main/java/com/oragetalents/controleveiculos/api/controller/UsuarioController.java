package com.oragetalents.controleveiculos.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragetalents.controleveiculos.api.dto.UsuarioDTO;
import com.oragetalents.controleveiculos.exception.RegraNegocioException;
import com.oragetalents.controleveiculos.model.entity.Usuario;
import com.oragetalents.controleveiculos.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	private UsuarioService service;
	
	public UsuarioController(UsuarioService service){
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = new Usuario(dto.getNome(),dto.getEmail(),dto.getCpf(),dto.getDataNascimento());
		try {
			service.salvarUsuario(usuario);
			return new ResponseEntity(usuario, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity ListaUsuarioUnico(@PathVariable("id") Long id) {
		Usuario usuario = null;
		try {
			usuario = service.findById(id);
			return new ResponseEntity(usuario,HttpStatus.ACCEPTED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
