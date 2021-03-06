package com.esds.servico.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esds.modelo.AssistenteSocial;
import com.esds.repositorio.Funcionarios;
import com.esds.servico.AssistenteSocialService;

@Service
public class AssistenteSocialServiceImpl implements AssistenteSocialService{
	
	private final Funcionarios funcionarios;
	
	public AssistenteSocialServiceImpl(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public AssistenteSocial salvar(AssistenteSocial funcionario) {
		return funcionarios.save(funcionario);
	}

	@Override
	public void atualizar(Integer id, AssistenteSocial funcionario) {
		
//		Optional<Funcionario> procurado = funcionarios.findById(id);
//
//		if (procurado.isPresent()) {
//			funcionario.setId(id);
//			funcionarios.save(funcionario);
//		} else {
//			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
//					"Funcionário não encontrado!");
//			throw exception;
//		}
		
		funcionarios.findById(id).map(f -> {
			funcionarios.delete(f);
			funcionario.setId(id);
			funcionarios.save(funcionario);
			return funcionario;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));
		
		
	}
	
	@Override
	public List<AssistenteSocial> buscarTodos() {
		List<AssistenteSocial> assistenteSociais = funcionarios.findAllAssistenteSocial();
		return assistenteSociais;
	}

	@Override
	public AssistenteSocial buscarPorId(Integer id) {
		return funcionarios.findAssistenteSocialById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Não foi possível localizar funcionário com o ID informado!"));
	}


}
