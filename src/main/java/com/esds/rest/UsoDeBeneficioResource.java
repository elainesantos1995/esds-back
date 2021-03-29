package com.esds.rest;

import java.util.Date;
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

import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.modelo.UsoDeBeneficio;
import com.esds.servico.BeneficioService;
import com.esds.servico.impl.InscricaoServiceImpl;
import com.esds.servico.impl.UsoDeBeneficioServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usosDeBeneficios/")
public class UsoDeBeneficioResource{
	
	@Autowired
	private UsoDeBeneficioServiceImpl usos;
	
	@Autowired
	private InscricaoServiceImpl inscricoes;
	
	@Autowired
	private BeneficioService beneficios;
	
	@PostMapping("{idInscricao}")
	@ResponseStatus(HttpStatus.CREATED)
	public UsoDeBeneficio salvar(@PathVariable Integer idInscricao, @RequestBody UsoDeBeneficio usoDeBeneficio) {
		
		Inscricao insc = this.inscricoes.buscarPorId(idInscricao);
		Beneficio beneficio = this.beneficios.buscarPorId(insc.getBeneficio().getId());
		
		insc.setQuantBeneficiosASeremRetiradados(insc.getQuantBeneficiosASeremRetiradados() - 1);
		insc.setQuantBeneficiosRetirados(insc.getQuantBeneficiosRetirados() + 1);
		
		this.inscricoes.atualizar(idInscricao, insc);
		
		usoDeBeneficio.setBeneficio(beneficio);
		usoDeBeneficio.setInscricao(insc);
		usoDeBeneficio.setDataDoUso(new Date());
		
		return usos.salvar(usoDeBeneficio);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody UsoDeBeneficio usoDeBeneficio) {
		usos.atualizar(id, usoDeBeneficio);
	}
	
	
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Integer id) {
		usos.remover(id);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsoDeBeneficio buscarPorId(@PathVariable Integer id) {
		return usos.buscarPorId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsoDeBeneficio> burcarTodos(){
		return usos.buscarTodos();
	}	
	
	@GetMapping("usos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<UsoDeBeneficio> buscarUsoDeUmBeneficiario(@PathVariable Integer id) {		
		return usos.buscarUsoDeUmBeneficiario(id);
	}

}
