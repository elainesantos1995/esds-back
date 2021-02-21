package com.esds.servico;

import com.esds.modelo.Entrevistador;

public interface EntrevistadorService {
	
	Entrevistador salvar(Entrevistador funcionario);
	void atualizar(Integer id, Entrevistador funcionario);

}
