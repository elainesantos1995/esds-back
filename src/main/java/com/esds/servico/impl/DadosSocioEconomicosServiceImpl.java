package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.DadosSocioEconomicos;
import com.esds.repositorio.DadosSocioEconomicosRepository;
import com.esds.servico.DadosSocioEconomicosService;

@Service
public class DadosSocioEconomicosServiceImpl implements DadosSocioEconomicosService{
	
	@Autowired
	private DadosSocioEconomicosRepository service;
	
	@Override
	public DadosSocioEconomicos salvar(DadosSocioEconomicos dados) {
		return service.save(dados);
	}

	@Override
	public void atualizar(Integer id, DadosSocioEconomicos dados) {
		
		Optional<DadosSocioEconomicos> procurado = service.findById(id);
		
		if(procurado.isPresent()) {
			dados.setId(id);
			service.save(dados);
		}else {
			RegraDeNegocioException exception = new RegraDeNegocioException("Não foi possível localizar dados com o ID informado!");
			throw exception;
		}		
	}

	@Override
	public DadosSocioEconomicos buscarPorId(Integer id) {
		
		DadosSocioEconomicos dados = service.findById(id)
				 .orElseThrow(() -> 
				 new RegraDeNegocioException("Não foi possível localizar dados com o ID informado!"));	
		 return dados;
	}

	@Override
	public List<DadosSocioEconomicos> buscarTodos() {
		return service.recuperarDadosComBeneficiario();
	}

	@Override
	public void remover(Integer id) {
		service.findById(id).map(dado -> {
			this.service.delete(dado);
			return dado;
			}).orElseThrow(() -> new RegraDeNegocioException("Não foi possível localizar dados com o ID informado!"));			
	}
	
	@Override
	 public List<DadosSocioEconomicos> recuperarDadosDeUmBeneficiario(Integer idBeneficiario){
		 return service.recuperarDadosDeUmBeneficiario(idBeneficiario);
	 }

}
