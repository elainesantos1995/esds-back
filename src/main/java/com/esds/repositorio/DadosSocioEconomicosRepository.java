package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.DadosSocioEconomicos;

public interface DadosSocioEconomicosRepository extends JpaRepository<DadosSocioEconomicos, Integer> {
	
	@Query("FROM DadosSocioEconomicos d JOIN FETCH d.beneficiarioTitular WHERE d.beneficiarioTitular.id = :id")
	List<DadosSocioEconomicos> recuperarDadosDeUmBeneficiario(@Param("id") Integer id);
	
	@Query("FROM DadosSocioEconomicos d JOIN FETCH d.beneficiarioTitular")
	List<DadosSocioEconomicos> recuperarDadosComBeneficiario();

}
