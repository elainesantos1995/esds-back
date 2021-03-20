package com.esds.servico;

import java.util.List;

import com.esds.modelo.DadosSocioEconomicos;

public interface DadosSocioEconomicosService {
	
	public DadosSocioEconomicos salvar(DadosSocioEconomicos dados);
	public void atualizar(Integer id, DadosSocioEconomicos dados);
	public DadosSocioEconomicos buscarPorId(Integer id);
	public List<DadosSocioEconomicos> buscarTodos();
	public void remover(Integer id);
	public List<DadosSocioEconomicos>recuperarDadosDeUmBeneficiario(Integer idBeneficiario);

}
