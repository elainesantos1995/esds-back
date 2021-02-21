package com.esds.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.esds.modelo.AssistenteSocial;
import com.esds.modelo.Funcionario;


public interface Funcionarios extends JpaRepository<Funcionario, Integer>{
	
	@Query("from AssistenteSocial")
	List<AssistenteSocial> findAllAssistenteSocial();
	 
	@Query("from AssistenteSocial where id = :id")
	Optional<AssistenteSocial> findAssistenteSocialById(@Param("id") Integer id);
	 
	Optional<Funcionario> findByLogin(String login);
	
	@Query("FROM Funcionario f JOIN FETCH f.endereco")
	List<Funcionario> findByFuncionarioFetchEagerEndereco();
	
	@Query("FROM Funcionario f JOIN FETCH f.endereco WHERE f.id = :id")
	List<Funcionario> findByIdFuncionarioFetchEndereco(@Param("id") Integer id);
	
	

}
