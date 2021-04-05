package com.esds.rest;

import java.util.List;

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

import com.esds.dto.BeneficioDTO;
import com.esds.dto.InscricaoDTO;
import com.esds.enumeracoes.Periodicidade;
import com.esds.enumeracoes.StatusInscricao;
import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.modelo.ProgramaSocial;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.InscricaoServiceImpl;
import com.esds.servico.impl.ProgramaSocialServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/beneficios")
public class BeneficiosResource {

	@Autowired
	private BeneficioServiceImpl beneficios;
	
	@Autowired
	private ProgramaSocialServiceImpl programas;
	
	@Autowired
	private InscricaoServiceImpl inscricoes;
	
	@PostMapping("{idPrograma}")
	@ResponseStatus(HttpStatus.CREATED)
	public Beneficio salvar(@RequestBody BeneficioDTO beneficioDTO, @PathVariable Integer idPrograma) {

		Beneficio beneficio = new Beneficio();
		beneficio.setNome(beneficioDTO.getNome());
		beneficio.setJustificativa(beneficioDTO.getJustificativa());
		beneficio.setControleCarteirinha(beneficioDTO.isControleCarteirinha());
		beneficio.setControleBiometria(beneficioDTO.isControleBiometria());
		beneficio.setControleDocumento(beneficioDTO.isControleDocumento());
		beneficio.setLimiteVagas(beneficioDTO.getLimiteVagas());
		beneficio.setToleranciaUsosCancelado(beneficioDTO.getToleranciaUsosCancelado());
		beneficio.setToleranciaUsosInadimplente(beneficioDTO.getToleranciaUsosInadimplente());
		beneficio.setTotalRecursosAportados(beneficioDTO.getTotalRecursosAportados());
		beneficio.setTotalBeneficios(beneficioDTO.getTotalBeneficios());
		
		Periodicidade periodicidade = Periodicidade.valueOf(beneficioDTO.getPeriodicidade());
		beneficio.setPeriodicidade(periodicidade);
		
		ProgramaSocial programa = programas.buscarPorId(idPrograma);

		beneficio.setPrograma(programa);

		Beneficio beneficioSalvo = this.beneficios.salvar(beneficio);

		programa.getBeneficios().add(beneficio);
		programas.atualizar(idPrograma, programa);

		return beneficioSalvo;
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody BeneficioDTO beneficioDTO) {

		Beneficio beneficio = new Beneficio();
		beneficio.setNome(beneficioDTO.getNome());
		beneficio.setJustificativa(beneficioDTO.getJustificativa());
		
		beneficio.setControleCarteirinha(beneficioDTO.isControleCarteirinha());
		beneficio.setControleBiometria(beneficioDTO.isControleBiometria());
		beneficio.setControleDocumento(beneficioDTO.isControleDocumento());
		beneficio.setLimiteVagas(beneficioDTO.getLimiteVagas());
		beneficio.setToleranciaUsosCancelado(beneficioDTO.getToleranciaUsosCancelado());
		beneficio.setToleranciaUsosInadimplente(beneficioDTO.getToleranciaUsosInadimplente());
		beneficio.setTotalRecursosAportados(beneficioDTO.getTotalRecursosAportados());
		beneficio.setTotalBeneficios(beneficioDTO.getTotalBeneficios());
		
		Periodicidade periodicidade = Periodicidade.valueOf(beneficioDTO.getPeriodicidade());
		beneficio.setPeriodicidade(periodicidade);
		
		ProgramaSocial programa = programas.buscarPorId(beneficioDTO.getIdPrograma());
		
		beneficio.setPrograma(programa);
		this.beneficios.atualizar(id, beneficio);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Beneficio> buscarTodos() {
		List<Beneficio> lista = this.beneficios.buscarTodos();
		return lista;
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Beneficio buscarPorId(@PathVariable Integer id) {
		return this.beneficios.buscarPorId(id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Integer id) {
		this.beneficios.remover(id);
	}
	
	@GetMapping("beneficiosDoPrograma/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Beneficio> buscarBeneficiosDeUmPrograma(@PathVariable Integer id) {
		System.out.println("aqui");
		return this.beneficios.listarBeneficiosPrograma(id);
	}
	
	 
	
}
