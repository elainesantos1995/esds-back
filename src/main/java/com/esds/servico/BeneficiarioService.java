package com.esds.servico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.esds.modelo.Beneficiario;

public interface BeneficiarioService {
	
	Beneficiario salvar(Beneficiario beneficiario);
	void atualizar(Integer id, Beneficiario beneficiario);
	void remover(Integer id);
	Beneficiario buscarPorId(Integer id);
	List<Beneficiario> buscarTodos();
	
	Page<Beneficiario> findAllPageable(Pageable pageable);
}
