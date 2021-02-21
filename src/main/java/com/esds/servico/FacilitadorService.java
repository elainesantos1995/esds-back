package com.esds.servico;

import com.esds.modelo.Facilitador;

public interface FacilitadorService {
	
	Facilitador salvar(Facilitador funcionario);
	void atualizar(Integer id, Facilitador funcionario);


}
