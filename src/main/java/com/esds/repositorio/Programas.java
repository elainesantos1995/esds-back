package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.esds.modelo.ProgramaSocial;

public interface Programas extends JpaRepository<ProgramaSocial, Integer>{
	
	@Query(value="select * from programa_social p left outer join beneficio b on p.id=b.programa_id where p.id= :id", nativeQuery = true)
	List<ProgramaSocial> findByIdProgramaFetchBeneficio(@Param("id") Integer id);
	
}
