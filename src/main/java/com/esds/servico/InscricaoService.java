package com.esds.servico;

import java.util.List;

import com.esds.modelo.Inscricao;

public interface InscricaoService {
	
	Inscricao salvar(Inscricao inscricao);
	void atualizar(Integer id, Inscricao inscricao);
	void remover(Integer id);
	Inscricao buscarPorId(Integer id);
	List<Inscricao> buscarTodos();
	List<Inscricao> buscarInscricoesDeUmBeneficiario(Integer id);

}
