package com.esds.servico;

import java.util.List;
import com.esds.modelo.Funcionario;

public interface FuncionarioService {
	
	void remover(Integer id);
	Funcionario buscarPorId(Integer id);
	List<Funcionario> buscarTodos();

}
