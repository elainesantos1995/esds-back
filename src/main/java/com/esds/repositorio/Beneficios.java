package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.Beneficio;

public interface Beneficios extends JpaRepository<Beneficio, Integer>{
	
	@Query("FROM Beneficio b WHERE b.programa.id = :id")
	List<Beneficio> beneficioDeProgramas(@Param("id") Integer id);

	@Query("FROM Beneficio b JOIN FETCH b.inscricoesContempladas WHERE b.id = :id")
	List<Beneficio> inscricoesContempladasPorBeneficio(@Param("id") Integer id);
}
