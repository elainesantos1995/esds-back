package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.ProgramaSocial;
import com.esds.repositorio.Programas;
import com.esds.servico.ProgramaSocialService;

@Service
public class ProgramaSocialServiceImpl implements ProgramaSocialService{
	
	@Autowired
	private Programas programas;

	@Override
	public ProgramaSocial salvar(ProgramaSocial programa) {
		return this.programas.save(programa);
	}

	@Override
	public void atualizar(Integer id, ProgramaSocial programa) {
		Optional<ProgramaSocial> procurado = this.programas.findById(id);
		
		if(procurado.isPresent()) {
			programa.setId(id);
			programas.save(programa);
		}else {
			RegraDeNegocioException exception = new RegraDeNegocioException("Não foi possível localizar programa com o ID informado!");
			throw exception;
		}		
	}

	@Override
	public ProgramaSocial buscarPorId(Integer id) {
		ProgramaSocial programaSocial = this.programas.findById(id)
				 .orElseThrow(() -> 
				 new RegraDeNegocioException("Não foi possível localizar programa com o ID informado!"));	
		 return programaSocial;
	}

	@Override
	public List<ProgramaSocial> buscarTodos() {
		return this.programas.findAll();
	}

	@Override
	public void remover(Integer id) {
		this.programas.findById(id).map(programa -> {
			this.programas.delete(programa);
			return programa;
			}).orElseThrow(() -> new RegraDeNegocioException("Não foi possível localizar programa com o ID informado!"));			
	}
	
	public List<ProgramaSocial> findByIdProgramaFetchBeneficio(Integer id){
		return this.programas.findByIdProgramaFetchBeneficio(id);
	}
}
