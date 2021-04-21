package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.Beneficiario;
import com.esds.modelo.Endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Beneficiarios extends JpaRepository<Beneficiario, Integer> {
	
	@Query(value = "SELECT e.* FROM endereco e LEFT JOIN beneficiario b ON e.id=b.id", nativeQuery = true)
	public Endereco retornarEndereco();
	
	@Query(value = "SELECT * FROM Beneficiario as b INNER JOIN Endereco as e ON b.endereco_id = e.id INNER JOIN Imagem as i ON b.imagem_id = i.id", nativeQuery = true) 
	List<Beneficiario> findByBeneficiarioFetchEagerEndereco();
	
	@Query(value = "SELECT * FROM Beneficiario as b INNER JOIN Endereco as e ON b.endereco_id = e.id INNER JOIN Imagem as i ON b.imagem_id = i.id", nativeQuery = true) 
	Page<Beneficiario> findByBeneficiarioFetchEagerEndereco(Pageable pageable);
	
	//getByID
	@Query("FROM Beneficiario b JOIN FETCH b.endereco WHERE b.id = :id")
	List<Beneficiario> findByIdBeneficiarioFetchEndereco(@Param("id") Integer id);
	
	@Query("FROM Beneficiario b WHERE b.cpf = :cpf")
	Beneficiario findByCPF(@Param("cpf") String cpf);

}
