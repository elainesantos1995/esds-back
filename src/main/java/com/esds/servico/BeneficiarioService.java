package com.esds.servico;

import java.util.List;

import com.esds.modelo.Beneficiario;

public interface BeneficiarioService {
	
	Beneficiario salvar(Beneficiario beneficiario);
	void atualizar(Integer id, Beneficiario beneficiario);
	void remover(Integer id);
	Beneficiario buscarPorId(Integer id);
	List<Beneficiario> buscarTodos();

}
