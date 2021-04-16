package com.esds.servico.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.UsoDeBeneficio;
import com.esds.repositorio.UsosDeBeneficios;
import com.esds.servico.UsoDeBeneficioService;

@Service
public class UsoDeBeneficioServiceImpl implements UsoDeBeneficioService{
	
	@Autowired
	private UsosDeBeneficios usos;

	@Override
	public UsoDeBeneficio salvar(UsoDeBeneficio uso) {
		return usos.save(uso);
	}

	@Override
	public void atualizar(Integer id, UsoDeBeneficio uso) {
		
		Optional<UsoDeBeneficio> usoRecuperado = usos.findById(id);
		
		if(usoRecuperado.isPresent()) {
			uso.setId(id);
			usos.save(uso);			
		}else {
			RegraDeNegocioException exception = new RegraDeNegocioException("Não foi possível localizar Uso De Benefício com o ID informado!");
			throw exception;
		}	
	}

	@Override
	public void remover(Integer id) {
		
	usos.findById(id).map(inscricao ->{
			this.usos.delete(inscricao);
			return inscricao;
		}).orElseThrow(() -> new RegraDeNegocioException("Não foi possível remover uso com o id informado!"));
		
	}
	
	@Override
	public UsoDeBeneficio buscarPorId(Integer id) {
	UsoDeBeneficio uso = usos.findById(id).orElseThrow(
				() -> new RegraDeNegocioException("Uso com id informado não encontrado!"));
		return uso;
	}

	@Override
	public List<UsoDeBeneficio> buscarTodos() {
		return usos.findAll();
	}

	public List<UsoDeBeneficio> buscarUsoDeUmBeneficiario(Integer id) {
		return usos.buscarUsoDeUmBeneficiario(id);
	}

	public List<UsoDeBeneficio> buscarUsoDeBeneficiarioEmPrograma(String cpf, Integer idPrograma) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UsoDeBeneficio> buscarUsoDeUmBeneficio(Integer id){
		return this.usos.buscarUsoDeUmBeneficio(id);
	}

	
}
