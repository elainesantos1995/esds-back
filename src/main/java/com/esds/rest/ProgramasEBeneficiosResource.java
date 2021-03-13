package com.esds.rest;

import java.util.ArrayList;
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
import com.esds.dto.ProgramaDTO;
import com.esds.enumeracoes.Periodicidade;
import com.esds.modelo.Beneficio;
import com.esds.modelo.ProgramaSocial;
import com.esds.servico.impl.ProgramaSocialServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/programas-e-beneficios")
public class ProgramasEBeneficiosResource{
	
	@Autowired
	private ProgramaSocialServiceImpl programas;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProgramaSocial salvar(@RequestBody ProgramaDTO programaDTO) {
		
		ProgramaSocial programa = new ProgramaSocial();
		programa.setNome(programaDTO.getNome());
		programa.setDescricao(programaDTO.getDescricao());
		programa.setVigenciaInicio(programaDTO.getVigenciaInicio());
		programa.setVigenciaTermino(programaDTO.getVigenciaTermino());
		
		ProgramaSocial programaSalvo = this.programas.salvar(programa);		
			
		return programaSalvo;		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody ProgramaDTO programaDTO) {
		
		ProgramaSocial programa = new ProgramaSocial();
		programa.setId(programaDTO.getId());
		programa.setNome(programaDTO.getNome());
		programa.setDescricao(programaDTO.getDescricao());
		programa.setVigenciaInicio(programaDTO.getVigenciaInicio());
		programa.setVigenciaTermino(programaDTO.getVigenciaTermino());

		this.programas.atualizar(id, programa);			
	}	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProgramaDTO> buscarTodos(){
		List<ProgramaSocial> programas = this.programas.buscarTodos();
		List<ProgramaDTO> programasDTO = new ArrayList<ProgramaDTO>();
		
		for(ProgramaSocial programa: programas) {
			ProgramaDTO programaDTO = new ProgramaDTO();
			programaDTO.setId(programa.getId());
			programaDTO.setNome(programa.getNome());
			programaDTO.setDescricao(programa.getDescricao());
			programaDTO.setVigenciaInicio(programa.getVigenciaInicio());
			programaDTO.setVigenciaTermino(programa.getVigenciaTermino());
			programaDTO.setBeneficios(new ArrayList<BeneficioDTO>());

			for(Beneficio beneficio : programa.getBeneficios()) {
				BeneficioDTO beneficioDTO = new BeneficioDTO();
				beneficioDTO.setId(beneficio.getId());
				beneficioDTO.setNome(beneficio.getNome());
				beneficioDTO.setJustificativa(beneficio.getJustificativa());
				beneficioDTO.setTotalRecursosAportados(beneficio.getTotalRecursosAportados());
				beneficioDTO.setLimiteVagas(beneficio.getLimiteVagas());
				beneficioDTO.setControleBiometria(beneficio.isControleBiometria());
				beneficioDTO.setControleDocumento(beneficio.isControleDocumento());
				beneficioDTO.setControleCarteirinha(beneficio.isControleCarteirinha());
//				
//				if(!beneficio.getPeriodicidade().getPeriodo().equals(null)) {
//					String periodicidade = beneficio.getPeriodicidade().getPeriodo();
//					beneficioDTO.setPeriodicidade(periodicidade);					
//				}
				
				beneficioDTO.setToleranciaUsosInadimplente(beneficio.getToleranciaUsosInadimplente());
				beneficioDTO.setToleranciaUsosCancelado(beneficio.getToleranciaUsosCancelado());
				programaDTO.getBeneficios().add(beneficioDTO);
			}
			
			programasDTO.add(programaDTO);
		}
		
		return programasDTO;
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProgramaSocial buscarPorId(@PathVariable Integer id){
		
		return programas.buscarPorId(id);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Integer id) {
		this.programas.remover(id);
	}
}

