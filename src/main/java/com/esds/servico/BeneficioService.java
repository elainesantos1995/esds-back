package com.esds.servico;

import java.util.List;

import com.esds.modelo.Beneficio;

public interface BeneficioService {
	
	public Beneficio salvar(Beneficio beneficio);
	public void atualizar(Integer id, Beneficio beneficio);
	public Beneficio buscarPorId(Integer id);
	public List<Beneficio> buscarTodos();
	public void remover(Integer id);

}
