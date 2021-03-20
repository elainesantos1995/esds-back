package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.Inscricao;

public interface Inscricoes extends JpaRepository<Inscricao, Integer>{
	
	@Query("FROM Inscricao i JOIN FETCH i.beneficiario JOIN FETCH i.beneficiario.imagem where i.beneficiario.id = :id")
	public List<Inscricao> buscarInscricoesDeUmBeneficiario(@Param("id") Integer id);
	

}
