package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.UsoDeBeneficio;

public interface UsosDeBeneficios extends JpaRepository<UsoDeBeneficio, Integer>{
	
	@Query("FROM UsoDeBeneficio u JOIN FETCH u.inscricao JOIN FETCH u.beneficio WHERE u.inscricao.id = :id")
//	@Query("FROM UsoDeBeneficio u WHERE u.inscricao.id = :id")
	public List<UsoDeBeneficio> buscarUsoDeUmBeneficiario(@Param("id") Integer id);

}
