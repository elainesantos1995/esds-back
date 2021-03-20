package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficiario;
import com.esds.repositorio.Beneficiarios;
import com.esds.servico.BeneficiarioService;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService{
	
	@Autowired
	private Beneficiarios beneficiarios;	

	@Override
	public Beneficiario salvar(Beneficiario beneficiario) {
		return this.beneficiarios.save(beneficiario);
	}
	
	public void atualizar(Integer id, Beneficiario beneficiario) {
		Optional<Beneficiario> procurado = beneficiarios.findById(id);

		if (procurado.isPresent()) {
			beneficiario.setId(id);
			beneficiarios.save(beneficiario);
		} else {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Beneficiário não encontrado!");
			throw exception;
		}
		
	}

	@Override
	public void remover(Integer id) {		
		beneficiarios.findById(id).map(beneficiario -> {
			this.beneficiarios.delete(beneficiario);
			return beneficiario;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));		
		
	}

	@Override
	public Beneficiario buscarPorId(Integer id) {
		 Beneficiario beneficiario = beneficiarios.findById(id)
				 .orElseThrow(() -> 
				 
				 // new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));	Aqui
				 new RegraDeNegocioException("Não foi possível localizar beneficiário com o ID informado!"));	

		 return beneficiario;
	}

	@Override
	public List<Beneficiario> buscarTodos() {
		return this.beneficiarios.findAll();
	}

	public List <Beneficiario> findByBeneficiarioFetchEagerEndereco(){
		return beneficiarios.findByBeneficiarioFetchEagerEndereco();
	}
	
	public List<Beneficiario> findByIdBeneficiarioFetchEndereco(Integer id){
		return beneficiarios.findByIdBeneficiarioFetchEndereco(id);
	}

	public Beneficiario findByCPF(String cpf){
		return beneficiarios.findByCPF(cpf);
	}

}
