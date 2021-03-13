package com.esds.servico.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficio;
import com.esds.repositorio.Beneficios;
import com.esds.servico.BeneficioService;

@Service
public class BeneficioServiceImpl implements BeneficioService{
	
	@Autowired
	private Beneficios beneficios;

	@Override
	public Beneficio salvar(Beneficio beneficio) {
		return beneficios.save(beneficio);
	}

	@Override
	public void atualizar(Integer id, Beneficio beneficio) {
		
		Optional<Beneficio> procurado = beneficios.findById(id);
		
		if(procurado.isPresent()) {
			beneficio.setId(id);
			beneficios.save(beneficio);
		}else {
			RegraDeNegocioException exception = new RegraDeNegocioException("Não foi possível localizar beneficio com o ID informado!");
			throw exception;
		}		
	}

	@Override
	public Beneficio buscarPorId(Integer id) {
		
		Beneficio beneficio = beneficios.findById(id)
				 .orElseThrow(() -> 
				 new RegraDeNegocioException("Não foi possível localizar beneficio com o ID informado!"));	
		 return beneficio;
	}

	@Override
	public List<Beneficio> buscarTodos() {
		return this.beneficios.findAll();
	}

	@Override
	public void remover(Integer id) {
		beneficios.findById(id).map(beneficio -> {
			this.beneficios.delete(beneficio);
			return beneficio;
			}).orElseThrow(() -> new RegraDeNegocioException("Não foi possível localizar beneficio com o ID informado!"));			
	}
	
	public List<Beneficio> listarBeneficiosPrograma(Integer id){
		
		System.out.println("______________________________________________________________________________________________________");

		return beneficios.beneficioDeProgramas(id);
	}

}
