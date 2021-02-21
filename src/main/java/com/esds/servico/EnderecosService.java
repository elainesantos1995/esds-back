package com.esds.servico;

import java.util.List;
import com.esds.modelo.Endereco;

public interface EnderecosService {
	
	Endereco salvar(Endereco endereco);
	void atualizar(Integer id, Endereco endereco);
	void remover(Integer id);
	Endereco buscarPorId(Integer id);
	List<Endereco> buscarTodos();

}
