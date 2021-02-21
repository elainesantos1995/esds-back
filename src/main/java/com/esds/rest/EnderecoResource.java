package com.esds.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esds.modelo.Endereco;
import com.esds.servico.impl.EnderecoServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoResource{
	
	@Autowired
	private EnderecoServiceImpl enderecos;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco salvar(@RequestBody Endereco endereco) {
		return enderecos.salvar(endereco);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody Endereco endereco) {
		enderecos.atualizar(id, endereco);		
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void remover(@PathVariable Integer id) {
		enderecos.remover(id);		
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Endereco buscarPorId(@PathVariable Integer id) {
		return enderecos.buscarPorId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Endereco retornarEnderecoPorUltimoId() {
		return enderecos.retornarEnderecoPorUltimoId();
	};
	
	

}
