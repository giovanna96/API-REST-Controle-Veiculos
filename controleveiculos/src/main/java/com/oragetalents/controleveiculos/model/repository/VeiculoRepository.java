package com.oragetalents.controleveiculos.model.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oragetalents.controleveiculos.model.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	@Query(value= "SELECT v FROM Veiculo v INNER JOIN v.usuario u where u.id =:id")
	Set<Veiculo> findAllVeiculosOfUsuario(@Param("id") Long id);
}
