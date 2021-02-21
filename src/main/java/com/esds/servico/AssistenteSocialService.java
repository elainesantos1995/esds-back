package com.esds.servico;

import java.util.List;

import com.esds.modelo.AssistenteSocial;

public interface AssistenteSocialService {
	
	AssistenteSocial salvar(AssistenteSocial funcionario);
	
	void atualizar(Integer id, AssistenteSocial funcionario);
	
	List<AssistenteSocial> buscarTodos();
	
	AssistenteSocial buscarPorId(Integer id);

}
