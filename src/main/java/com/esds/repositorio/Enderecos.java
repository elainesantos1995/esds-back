package com.esds.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esds.modelo.Endereco;

public interface Enderecos extends JpaRepository<Endereco, Integer>{
	
	@Query(value = "Select * from endereco as e where e.id = ( select MAX(ID) FROM endereco)", nativeQuery = true)
	public Endereco retornarEnderecoPorUltimoId();
	
}
