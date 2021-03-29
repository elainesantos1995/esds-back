package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.Inscricao;

public interface Inscricoes extends JpaRepository<Inscricao, Integer>{
	
	@Query("from Inscricao i LEFT JOIN Beneficio b ON i.beneficio.id=b.id WHERE i.status = 1 AND i.beneficiario.id = :id")
	public List<Inscricao> buscarInscricoesDeUmBeneficiario(@Param("id") Integer id);
	 
	//Pega as inscrições relacionadas a um benefício
	@Query("FROM Inscricao i JOIN FETCH i.beneficio WHERE i.beneficio.id= :id")
	public List<Inscricao> buscarInscricoesEmUmBeneficio(@Param("id") Integer id);
	
	@Query("FROM Inscricao i JOIN FETCH i.beneficiario WHERE i.status = 1 AND i.id = :id")
	public List<Inscricao> buscarInscricoesSelecionadas(@Param("id") Integer id);

}
