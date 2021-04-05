package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.esds.modelo.ProgramaSocial;

public interface Programas extends JpaRepository<ProgramaSocial, Integer>{
	
	@Query(value="select * from programa_social p left outer join beneficio b on p.id=b.programa_id where p.id= :id order by p.ano desc", nativeQuery = true)
	List<ProgramaSocial> findByIdProgramaFetchBeneficio(@Param("id") Integer id);
	
	@Query("FROM ProgramaSocial p WHERE p.ano = :ano ORDER BY p.ano DESC")
	List<ProgramaSocial> findByAnoProgramaFetchBeneficio(@Param("ano") Integer ano);
	
	
	
}
