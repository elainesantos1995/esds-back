package com.esds.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esds.modelo.Imagem;

public interface Imagens extends JpaRepository<Imagem, Integer>{
	
	 Optional<Imagem> findByName(String name);
	 
	 @Query(value = "Select * from imagem as i where i.id = ( select MAX(ID) FROM imagem)", nativeQuery = true)
	public  Optional<Imagem> retornarImagemPorUltimoId();

}
