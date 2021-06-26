package com.oragetalents.controleveiculos.api.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragetalents.controleveiculos.api.dto.VeiculoDTO;
import com.oragetalents.controleveiculos.exception.RegraNegocioException;
import com.oragetalents.controleveiculos.model.entity.Usuario;
import com.oragetalents.controleveiculos.model.entity.Veiculo;
import com.oragetalents.controleveiculos.service.UsuarioService;
import com.oragetalents.controleveiculos.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {
	private  VeiculoService service;
	private  UsuarioService usuarioService;

	public VeiculoController(VeiculoService service,UsuarioService usuarioService) {
		this.service = service;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody VeiculoDTO dto) { 
		Usuario usuario = usuarioService.findById(dto.getUsuarioId());
		Veiculo veiculo = new Veiculo(dto.getMarca(),dto.getModelo(),dto.getAno(),new BigDecimal(dto.getValor()),usuario);
		try {
			service.salvarVeiculo(veiculo);
			return new ResponseEntity(veiculo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
