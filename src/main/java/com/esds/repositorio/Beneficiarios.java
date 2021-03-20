package com.esds.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esds.modelo.Beneficiario;
import com.esds.modelo.Endereco;

public interface Beneficiarios extends JpaRepository<Beneficiario, Integer> {
	
	@Query(value = "SELECT e.* FROM endereco e LEFT JOIN beneficiario b ON e.id=b.id", nativeQuery = true)
	public Endereco retornarEndereco();
	
	//Deletar se estiver sem uso (verificar)
	@Query(value = "select e.* from endereco e left join beneficiario b on e.id=b.id", nativeQuery = true)
	public Endereco findByIdOrderById();
	
	// Buscar beneficiário com endereço e com imagem
	@Query("FROM Beneficiario b JOIN FETCH b.endereco JOIN FETCH b.imagem")
	List<Beneficiario> findByBeneficiarioFetchEagerEndereco();
	
	//Utilizado no getByID
	@Query("FROM Beneficiario b JOIN FETCH b.endereco WHERE b.id = :id")
	List<Beneficiario> findByIdBeneficiarioFetchEndereco(@Param("id") Integer id);
	
	@Query("FROM Beneficiario b WHERE b.cpf = :cpf")
//	@Query(value="SELECT b * FROM beneficiario as b where b.cpf = :cpf", nativeQuery = true)
	Beneficiario findByCPF(@Param("cpf") String cpf);

}
