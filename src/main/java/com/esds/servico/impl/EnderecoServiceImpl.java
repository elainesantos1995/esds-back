package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Endereco;
import com.esds.repositorio.Enderecos;
import com.esds.servico.EnderecosService;

@Service
public class EnderecoServiceImpl implements EnderecosService{
	
	@Autowired
	private Enderecos enderecos;

	@Override
	public Endereco salvar(Endereco endereco) {
		return enderecos.save(endereco);
	}

	@Override
	public void atualizar(Integer id, Endereco endereco) {
		Optional<Endereco> enderecoProcurado = enderecos.findById(id);
		
		if(enderecoProcurado.isPresent()) {
			endereco.setId(id);
			enderecos.save(endereco);
		}else {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Endereço não encontrado!");
			throw exception;
		}		
	}

	@Override
	public void remover(Integer id) {		
		enderecos.findById(id).map(endereco -> {
			enderecos.delete(endereco);
			return endereco;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));		
	}

	@Override
	public Endereco buscarPorId(Integer id) {
		
		return enderecos.findById(id)
				.orElseThrow(() ->
				new RegraDeNegocioException("Não foi possível localizar funcionário com o ID informado!"));
	}

	@Override
	public List<Endereco> buscarTodos() {
		return enderecos.findAll();
	}
	
	public Endereco retornarEnderecoPorUltimoId() {
		return enderecos.retornarEnderecoPorUltimoId();
	}

}
