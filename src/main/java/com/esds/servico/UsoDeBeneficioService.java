package com.esds.servico;

import java.util.List;

import com.esds.modelo.UsoDeBeneficio;

public interface UsoDeBeneficioService {
	
	UsoDeBeneficio salvar(UsoDeBeneficio uso);
	void atualizar(Integer id, UsoDeBeneficio uso);
	void remover(Integer id);
	UsoDeBeneficio buscarPorId(Integer id);
	List<UsoDeBeneficio> buscarTodos();
	List<UsoDeBeneficio> buscarUsoDeUmBeneficiario(String cpf);
	List<UsoDeBeneficio> buscarUsoDeBeneficiarioEmPrograma(String cpf, Integer idPrograma);

}
