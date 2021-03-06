package com.esds.rest;

import java.util.ArrayList;
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

import com.esds.dto.InscricaoDTO;
import com.esds.dto.InscricaoSelecionadaDTO;
import com.esds.enumeracoes.StatusInscricao;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.servico.impl.BeneficiarioServiceImpl;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.InscricaoServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoResource {
	
	@Autowired
	private InscricaoServiceImpl inscricoes;
	
	@Autowired
	private BeneficiarioServiceImpl beneficiarios;
	
	@Autowired
	private BeneficioServiceImpl beneficios;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Inscricao salvar(@RequestBody InscricaoDTO inscricaoDTO) {
		
		Inscricao inscricao = new Inscricao();
		
		Date dataInscricao = new Date();	
		inscricao.setDataInscricao(dataInscricao);
		inscricao.setParecer(inscricaoDTO.getParecer());
		inscricao.setStatus(StatusInscricao.EM_ANALISE);
		inscricao.setQuantBeneficiosASeremRetiradados(0);
		inscricao.setQuantBeneficiosRetirados(0);		
		
		Beneficiario beneficiario = beneficiarios.findByCPF(inscricaoDTO.getCpfBeneficiario());
		inscricao.setBeneficiario(beneficiario);
		
		Beneficio beneficio = beneficios.buscarPorId(inscricaoDTO.getIdBeneficio());
		inscricao.setBeneficio(beneficio);
				
		return inscricoes.salvar(inscricao);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody InscricaoDTO inscricaoDTO) {
		
		Inscricao inscricao = new Inscricao();
		
		System.out.println("id: "+ id);
		System.out.println("inscrição DTO: "+ inscricaoDTO.toString());
	
		inscricao.setDataInscricao(inscricaoDTO.getDataInscricao());
		inscricao.setParecer(inscricaoDTO.getParecer());
		StatusInscricao status = StatusInscricao.valueOf(inscricaoDTO.getStatus());
		inscricao.setStatus(status);
		inscricao.setQuantBeneficiosASeremRetiradados(inscricaoDTO.getQuantBeneficiosASeremRetiradados());
		inscricao.setQuantBeneficiosRetirados(inscricaoDTO.getQuantBeneficiosRetirados());		
		
		Beneficiario beneficiario = beneficiarios.findByCPF(inscricaoDTO.getCpfBeneficiario());
		inscricao.setBeneficiario(beneficiario);
		
		Beneficio beneficio = beneficios.buscarPorId(inscricaoDTO.getIdBeneficio());
		inscricao.setBeneficio(beneficio);
				
		inscricoes.atualizar(id, inscricao);
	}
	
	
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Integer id) {		
		inscricoes.remover(id);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public InscricaoDTO buscarPorId(@PathVariable Integer id) {
		
		InscricaoDTO inscricaoDTO = new InscricaoDTO();
		Inscricao inscricao = inscricoes.buscarPorId(id);
		inscricaoDTO.setBeneficio(inscricao.getBeneficio());
		inscricaoDTO.setBeneficiario(inscricao.getBeneficiario());
		inscricaoDTO.setDataInscricao(inscricao.getDataInscricao());
		inscricaoDTO.setId(inscricao.getId());
		inscricaoDTO.setParecer(inscricao.getParecer());
		inscricaoDTO.setCpfBeneficiario(inscricao.getBeneficiario().getCpf());
		inscricaoDTO.setQuantBeneficiosASeremRetiradados(inscricao.getQuantBeneficiosASeremRetiradados());
		inscricaoDTO.setQuantBeneficiosRetirados(inscricao.getQuantBeneficiosRetirados());
		inscricaoDTO.setStatus(inscricao.getStatus().getStatus());
		inscricaoDTO.setIdBeneficio(inscricao.getBeneficio().getId());
		
		return inscricaoDTO;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Inscricao> buscarTodos(){
		return inscricoes.buscarTodos();
	}
	
	@GetMapping("/inscricoes/beneficios/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Inscricao> buscarInscricoesEmUmBeneficio(@PathVariable Integer id){
		return inscricoes.buscarInscricoesEmUmBeneficio(id);
	}
	
	@PutMapping("beneficios/selecionados/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public List<Inscricao> selecionarInscricoes(@PathVariable Integer id, @RequestBody List<InscricaoDTO> inscricoes){
		
		Beneficio beneficio = this.beneficios.buscarPorId(id);
		List<Inscricao> inscricoesDoPrograma = new ArrayList<Inscricao>();
		List<Inscricao> inscricoesTotal = this.inscricoes.buscarInscricoesEmUmBeneficio(id);
		
		for(Inscricao insc: inscricoesTotal) {
			insc.setStatus(StatusInscricao.EM_LISTA);
			this.inscricoes.atualizar(insc.getId(), insc);
	
		}
		
		for(InscricaoDTO inscricaoDTO: inscricoes) {
			Inscricao inscricao = this.inscricoes.buscarPorId(inscricaoDTO.getId());
			inscricao.setStatus(StatusInscricao.DEFERIDA);
			inscricoesDoPrograma.add(inscricao);
			inscricao.setQuantBeneficiosASeremRetiradados(beneficio.getTotalBeneficios());
			this.inscricoes.atualizar(inscricaoDTO.getId(), inscricao);
		}
		
		beneficio.setInscricoesContempladas(inscricoesDoPrograma);
		
		this.beneficios.atualizar(id, beneficio);
		return beneficio.getInscricoesContempladas();
	}
	 
	@GetMapping("listagem/selecionados/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<InscricaoSelecionadaDTO> buscarInscricoesSelecionadas(@PathVariable Integer id){
		
		List<Inscricao> inscricoesRetornadas = this.inscricoes.buscarInscricoesSelecionadas(id);
		List<InscricaoSelecionadaDTO> inscricoesContempladas = new ArrayList<InscricaoSelecionadaDTO>();
		
		System.out.println("Inscrições selecionadas");
		System.out.println(inscricoesRetornadas.size());
		
		for(Inscricao insc: inscricoesRetornadas) {
			
			
			InscricaoSelecionadaDTO insSelDTO = new InscricaoSelecionadaDTO();
			insSelDTO.setId(insc.getId());
			insSelDTO.setBeneficio(insc.getBeneficio().getNome());
			insSelDTO.setStatus(insc.getStatus().getStatus());
			insSelDTO.setNome(insc.getBeneficiario().getNome());
			insSelDTO.setCpf(insc.getBeneficiario().getCpf());
			
			inscricoesContempladas.add(insSelDTO);
		}
		
		return inscricoesContempladas;
	}
	
	@GetMapping("/inscricoes/beneficiario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Inscricao> buscarInscricoesDeUmBeneficiario(@PathVariable Integer id){
		return inscricoes.buscarInscricoesDeUmBeneficiario(id);
	}

	
}
