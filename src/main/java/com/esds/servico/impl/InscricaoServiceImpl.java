package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Inscricao;
import com.esds.repositorio.Inscricoes;
import com.esds.servico.InscricaoService;

@Service
public class InscricaoServiceImpl implements InscricaoService{
	
	@Autowired
	private Inscricoes inscricoes;

	@Override	
	public Inscricao salvar(Inscricao inscricao) {
		return inscricoes.save(inscricao);
	}

	@Override
	public void atualizar(Integer id, Inscricao inscricao) {
		
		Optional<Inscricao> inscRecuperada = inscricoes.findById(id);
		
		if(inscRecuperada.isPresent()) {
			inscricao.setId(id);
			inscricoes.save(inscricao);			
		}else {
			RegraDeNegocioException exception = new RegraDeNegocioException("Não foi possível localizar inscrição com o ID informado!");
			throw exception;
		}			
	}

	@Override
	public void remover(Integer id) {
		
		inscricoes.findById(id).map(inscricao ->{
			this.inscricoes.delete(inscricao);
			return inscricao;
		}).orElseThrow(() -> new RegraDeNegocioException("Não foi possível remover inscrição com o id informado"));	
		
	}

	@Override
	public Inscricao buscarPorId(Integer id) {
		
		Inscricao inscricao = inscricoes.findById(id).orElseThrow(
				() -> new RegraDeNegocioException("Inscrição com id informado não encontrada!"));
		return inscricao;
	}

	@Override
	public List<Inscricao> buscarTodos() {		
		return inscricoes.findAll();
	}

	@Override
	public List<Inscricao> buscarInscricoesDeUmBeneficiario(Integer id) {		
		return inscricoes.buscarInscricoesDeUmBeneficiario(id);
	}

}
