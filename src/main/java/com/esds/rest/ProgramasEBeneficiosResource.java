package com.esds.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.esds.dto.ProgramaDTO;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Beneficio;
import com.esds.modelo.ProgramaSocial;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.ProgramaSocialServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/programas-e-beneficios")
public class ProgramasEBeneficiosResource{
	
	@Autowired
	private BeneficioServiceImpl beneficios;
	
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
		
		programa.setBeneficios(programaDTO.getBeneficios());
		
		ProgramaSocial programaSalvo = this.programas.salvar(programa);		
		
		if(programaDTO.getBeneficios() != null) {
					
		for(Beneficio be: programaDTO.getBeneficios()) {
			Beneficio beneficio = new Beneficio();
			beneficio.setNome(be.getNome());
			beneficio.setJustificativa(be.getJustificativa());
			beneficio.setPeriodicidade(be.getPeriodicidade());
			beneficio.setControleCarteirinha(be.isControleCarteirinha());
			beneficio.setControleBiometria(be.isControleBiometria());
			beneficio.setControleDocumento(be.isControleDocumento());
			beneficio.setLimiteVagas(be.getLimiteVagas());
			beneficio.setToleranciaUsosCancelado(be.getToleranciaUsosCancelado());
			beneficio.setToleranciaUsosInadimplente(be.getToleranciaUsosInadimplente());
			beneficio.setTotalRecursosAportados(be.getTotalRecursosAportados());			
			beneficio.setPrograma(programaSalvo);
			
			this.beneficios.salvar(beneficio);
					
			}	
		}	
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
		
		programa.setBeneficios(programaDTO.getBeneficios());
		
		this.programas.atualizar(id, programa);		
		
		if(programaDTO.getBeneficios() != null) {
					
		for(Beneficio be: programaDTO.getBeneficios()) {
			Beneficio beneficio = new Beneficio();
			beneficio.setNome(be.getNome());
			beneficio.setJustificativa(be.getJustificativa());
			beneficio.setPeriodicidade(be.getPeriodicidade());
			beneficio.setControleCarteirinha(be.isControleCarteirinha());
			beneficio.setControleBiometria(be.isControleBiometria());
			beneficio.setControleDocumento(be.isControleDocumento());
			beneficio.setLimiteVagas(be.getLimiteVagas());
			beneficio.setToleranciaUsosCancelado(be.getToleranciaUsosCancelado());
			beneficio.setToleranciaUsosInadimplente(be.getToleranciaUsosInadimplente());
			beneficio.setTotalRecursosAportados(be.getTotalRecursosAportados());			
			beneficio.setPrograma(programa);
			
			this.beneficios.atualizar(beneficio.getId(), beneficio);					
			}	
		}			
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
		
		programasDTO.add(programaDTO);
		}
		
		return programasDTO;
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProgramaSocial buscarPorId(@PathVariable Integer id){
		
//		List<ProgramaSocial> prog = programas.findByIdProgramaFetchBeneficio(id);
//		ProgramaSocial programaSocial = prog.get(0);
//		
//		List<Beneficio> beneficios = programaSocial.getBeneficios();
//		for(Beneficio beneficio: beneficios) {
//			System.out.println(beneficio.getNome());
//		}		
//		return programaSocial;
		return programas.buscarPorId(id);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Integer id) {
		this.programas.remover(id);
	}
}

