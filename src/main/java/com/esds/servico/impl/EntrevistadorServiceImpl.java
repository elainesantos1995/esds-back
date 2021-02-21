package com.esds.servico.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esds.modelo.Entrevistador;
import com.esds.modelo.Funcionario;
import com.esds.repositorio.Funcionarios;
import com.esds.servico.EntrevistadorService;

@Service
public class EntrevistadorServiceImpl implements EntrevistadorService{
	
	private final Funcionarios funcionarios;
	
	public EntrevistadorServiceImpl(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public Entrevistador salvar(Entrevistador funcionario) {
		return funcionarios.save(funcionario);
	}

	@Override
	public void atualizar(Integer id, Entrevistador funcionario) {
		
		Optional<Funcionario> procurado = funcionarios.findById(id);

		if (procurado.isPresent()) {
			funcionario.setId(id);
			funcionarios.save(funcionario);
		} else {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Funcionário não encontrado!");
			throw exception;
		}
		
	}

}
