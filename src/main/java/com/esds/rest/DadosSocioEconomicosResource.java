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

import com.esds.dto.DadosSocioEconomicosDTO;
import com.esds.enumeracoes.ComposicaoDomicilio;
import com.esds.enumeracoes.CondicaoDaResidencia;
import com.esds.enumeracoes.LocalidadeDomicilio;
import com.esds.enumeracoes.QuanMembrosIdosos;
import com.esds.enumeracoes.QuantComodos;
import com.esds.enumeracoes.QuantMembrosCrianca;
import com.esds.enumeracoes.QuantMembrosFamilia;
import com.esds.enumeracoes.RendaPerCapita;
import com.esds.enumeracoes.TipoDeTrabalho;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.DadosSocioEconomicos;
import com.esds.servico.impl.BeneficiarioServiceImpl;
import com.esds.servico.impl.DadosSocioEconomicosServiceImpl;
import com.esds.utils.Selecao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dadosSocioEconomicos")
public class DadosSocioEconomicosResource {
	
	@Autowired
	private DadosSocioEconomicosServiceImpl dados;
	
	@Autowired
	private BeneficiarioServiceImpl beneficiarios;
	
	@PostMapping("{idBeneficiario}")
	@ResponseStatus(HttpStatus.CREATED)
	public DadosSocioEconomicos salvar(@PathVariable String idBeneficiario, @RequestBody DadosSocioEconomicosDTO dadosDTO) {
		
		//Não tá pegando as doenças crôncas
		//Não tá pegando os nomes dos beneficiários dependentes
		
		DadosSocioEconomicos dadosSocioeconomicos = new DadosSocioEconomicos();
		
		ComposicaoDomicilio composicaoDomicilio = ComposicaoDomicilio.valueOf(dadosDTO.getComposicaoDomicilio());
		LocalidadeDomicilio localidadeDomicilio = LocalidadeDomicilio.valueOf(dadosDTO.getLocalidade());
		CondicaoDaResidencia condicaoDaResidencia = CondicaoDaResidencia.valueOf(dadosDTO.getCondicaoResidencia());
		QuantComodos quantComodos = QuantComodos.valueOf(dadosDTO.getQuantComodos());
		QuantMembrosFamilia quantMembrosFamilia = QuantMembrosFamilia.valueOf(dadosDTO.getQuantMembrosFamilia());
		QuanMembrosIdosos quantMembrosIdosos = QuanMembrosIdosos.valueOf(dadosDTO.getQuantMembrosIdosos());
		TipoDeTrabalho tipoDeTrabalho = TipoDeTrabalho.valueOf(dadosDTO.getTipoDeTrabalho());
		RendaPerCapita rendaPerCapita = RendaPerCapita.valueOf(dadosDTO.getRendaPerCapita());
		QuantMembrosCrianca quantMembrosCriancas = QuantMembrosCrianca.valueOf(dadosDTO.getQuantMembrosCriancas());
				
		dadosSocioeconomicos.setComposicaoDomicilio(composicaoDomicilio);
		dadosSocioeconomicos.setLocalidade(localidadeDomicilio);
		dadosSocioeconomicos.setCondicaoResidencia(condicaoDaResidencia);
		dadosSocioeconomicos.setQuantComodos(quantComodos);
		dadosSocioeconomicos.setQuantMembrosFamilia(quantMembrosFamilia);
		dadosSocioeconomicos.setQuantMembrosIdosos(quantMembrosIdosos);
		dadosSocioeconomicos.setTipoDeTrabalho(tipoDeTrabalho);
		dadosSocioeconomicos.setRendaPerCapita(rendaPerCapita);
		dadosSocioeconomicos.setQuantMembrosCriancas(quantMembrosCriancas);
		
		dadosSocioeconomicos.setAguaEncanada(dadosDTO.isAguaEncanada());
		dadosSocioeconomicos.setColetaEsgoto(dadosDTO.isColetaEsgoto());
		dadosSocioeconomicos.setDoencaCronica(dadosDTO.isDoencaCronica());
		dadosSocioeconomicos.setEnergiaEletrica(dadosDTO.isEnergiaEletrica());
		dadosSocioeconomicos.setFamiliaIndigena(dadosDTO.isFamiliaIndigena());
		dadosSocioeconomicos.setFamiliaQuilombola(dadosDTO.isFamiliaQuilombola());
		dadosSocioeconomicos.setMembroDeficiente(dadosDTO.isMembroDeficiente());
		dadosSocioeconomicos.setMembroEmpregado(dadosDTO.isMembroEmpregado());
		
		dadosSocioeconomicos.setRendaFamiliar(dadosDTO.getRendaFamiliar());  
		dadosSocioeconomicos.setQuantMembroDeficiente(dadosDTO.getQuantMembroDeficiente());
		dadosSocioeconomicos.setTipoDeficiencia(dadosDTO.getTipoDeficiencia());
		
		Date dataPreenchimento = new Date();
		dadosSocioeconomicos.setDataPreenchimento(dataPreenchimento);
		
		Date dataUltimaAtualizao = new Date();
		dadosSocioeconomicos.setDataUltimaAtualizacao(dataUltimaAtualizao);
		
//		dadosSocioeconomicos.setParecer(dadosDTO.getParecer());
		
		Selecao selecao = new Selecao();
		
		dadosSocioeconomicos.setPontuacao(selecao.calcularPontuacao(dadosSocioeconomicos));
		
		try {
			Beneficiario beneficiarioTitular = beneficiarios.findByCPF(idBeneficiario);
			dadosSocioeconomicos.setBeneficiarioTitular(beneficiarioTitular);
			
		} catch (Exception e) {
			
		}
		return this.dados.salvar(dadosSocioeconomicos);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody DadosSocioEconomicosDTO dadosDTO) {
		
		System.out.println(dadosDTO.toString());
		
		DadosSocioEconomicos dadosSocioeconomicos = new DadosSocioEconomicos();
		
		ComposicaoDomicilio composicaoDomicilio = ComposicaoDomicilio.valueOf(dadosDTO.getComposicaoDomicilio());
		LocalidadeDomicilio localidadeDomicilio = LocalidadeDomicilio.valueOf(dadosDTO.getLocalidade());
		CondicaoDaResidencia condicaoDaResidencia = CondicaoDaResidencia.valueOf(dadosDTO.getCondicaoResidencia());
		QuantComodos quantComodos = QuantComodos.valueOf(dadosDTO.getQuantComodos());
		QuantMembrosFamilia quantMembrosFamilia = QuantMembrosFamilia.valueOf(dadosDTO.getQuantMembrosFamilia());
		QuanMembrosIdosos quantMembrosIdosos = QuanMembrosIdosos.valueOf(dadosDTO.getQuantMembrosIdosos());
		TipoDeTrabalho tipoDeTrabalho = TipoDeTrabalho.valueOf(dadosDTO.getTipoDeTrabalho());
		RendaPerCapita rendaPerCapita = RendaPerCapita.valueOf(dadosDTO.getRendaPerCapita());
		QuantMembrosCrianca quantMembrosCriancas = QuantMembrosCrianca.valueOf(dadosDTO.getQuantMembrosCriancas());
				
		dadosSocioeconomicos.setComposicaoDomicilio(composicaoDomicilio);
		dadosSocioeconomicos.setLocalidade(localidadeDomicilio);
		dadosSocioeconomicos.setCondicaoResidencia(condicaoDaResidencia);
		dadosSocioeconomicos.setQuantComodos(quantComodos);
		dadosSocioeconomicos.setQuantMembrosFamilia(quantMembrosFamilia);
		dadosSocioeconomicos.setQuantMembrosIdosos(quantMembrosIdosos);
		dadosSocioeconomicos.setTipoDeTrabalho(tipoDeTrabalho);
		dadosSocioeconomicos.setRendaPerCapita(rendaPerCapita);
		dadosSocioeconomicos.setQuantMembrosCriancas(quantMembrosCriancas);
		
		dadosSocioeconomicos.setAguaEncanada(dadosDTO.isAguaEncanada());
		dadosSocioeconomicos.setColetaEsgoto(dadosDTO.isColetaEsgoto());
		dadosSocioeconomicos.setDoencaCronica(dadosDTO.isDoencaCronica());
		dadosSocioeconomicos.setEnergiaEletrica(dadosDTO.isEnergiaEletrica());
		dadosSocioeconomicos.setFamiliaIndigena(dadosDTO.isFamiliaIndigena());
		dadosSocioeconomicos.setFamiliaQuilombola(dadosDTO.isFamiliaQuilombola());
		dadosSocioeconomicos.setMembroDeficiente(dadosDTO.isMembroDeficiente());
		dadosSocioeconomicos.setMembroEmpregado(dadosDTO.isMembroEmpregado());
		dadosSocioeconomicos.setTemBanheiro(dadosDTO.isTemBanheiro());
		
		dadosSocioeconomicos.setRendaFamiliar(dadosDTO.getRendaFamiliar());  
		dadosSocioeconomicos.setQuantMembroDeficiente(dadosDTO.getQuantMembroDeficiente());
		dadosSocioeconomicos.setTipoDeficiencia(dadosDTO.getTipoDeficiencia());
		
		dadosSocioeconomicos.setDataPreenchimento(dadosDTO.getDataPreenchimento());
		
		Date dataUltimaAtualizacao = new Date();
		dadosSocioeconomicos.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		
		dadosSocioeconomicos.setPontuacao(dadosDTO.getPontuacao());
		
		dadosSocioeconomicos.setRendaFamiliar(dadosDTO.getRendaFamiliar());
		
		dadosSocioeconomicos.setParecer(dadosDTO.getParecer());
				
		try {
			Beneficiario beneficiarioTitular = beneficiarios.findByCPF(dadosDTO.getIdBeneficiario());
			dadosSocioeconomicos.setBeneficiarioTitular(beneficiarioTitular);
			
		} catch (Exception e) {
			
		}
		this.dados.atualizar(id, dadosSocioeconomicos);		
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<DadosSocioEconomicos> buscarTodos() {
		//Retornar DTO
		return this.dados.buscarTodos();
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public DadosSocioEconomicosDTO buscarPorId(@PathVariable Integer id) {
		
		DadosSocioEconomicosDTO dadosDTO = new DadosSocioEconomicosDTO();
		DadosSocioEconomicos dadosSocioeconomicos = this.dados.buscarPorId(id);
		
		dadosDTO.setId(dadosSocioeconomicos.getId());
		
		dadosDTO.setComposicaoDomicilio(dadosSocioeconomicos.getComposicaoDomicilio().getValor());
		dadosDTO.setLocalidade(dadosSocioeconomicos.getLocalidade().getValor());
		dadosDTO.setCondicaoResidencia(dadosSocioeconomicos.getCondicaoResidencia().getValor());
		dadosDTO.setQuantComodos(dadosSocioeconomicos.getQuantComodos().getValor());
		dadosDTO.setQuantMembrosFamilia(dadosSocioeconomicos.getQuantMembrosFamilia().getValor());
		dadosDTO.setQuantMembrosIdosos(dadosSocioeconomicos.getQuantMembrosIdosos().getValor());
		dadosDTO.setTipoDeTrabalho(dadosSocioeconomicos.getTipoDeTrabalho().getValor());
		dadosDTO.setRendaPerCapita(dadosSocioeconomicos.getRendaPerCapita().getValor());
		dadosDTO.setQuantMembrosCriancas(dadosSocioeconomicos.getQuantMembrosCriancas().getValor());
		
		dadosDTO.setAguaEncanada(dadosSocioeconomicos.isAguaEncanada());
		dadosDTO.setColetaEsgoto(dadosSocioeconomicos.isColetaEsgoto());
		dadosDTO.setDoencaCronica(dadosSocioeconomicos.isDoencaCronica());
		dadosDTO.setEnergiaEletrica(dadosSocioeconomicos.isEnergiaEletrica());
		dadosDTO.setFamiliaIndigena(dadosSocioeconomicos.isFamiliaIndigena());
		dadosDTO.setFamiliaQuilombola(dadosSocioeconomicos.isFamiliaQuilombola());
		dadosDTO.setMembroDeficiente(dadosSocioeconomicos.isMembroDeficiente());
		dadosDTO.setMembroEmpregado(dadosSocioeconomicos.isMembroEmpregado());
		
		dadosDTO.setTemBanheiro(dadosSocioeconomicos.isTemBanheiro());
		
		dadosDTO.setRendaFamiliar(dadosSocioeconomicos.getRendaFamiliar());  
		dadosDTO.setQuantMembroDeficiente(dadosSocioeconomicos.getQuantMembroDeficiente());
		dadosDTO.setTipoDeficiencia(dadosSocioeconomicos.getTipoDeficiencia());
		
		dadosDTO.setIdBeneficiario(dadosSocioeconomicos.getBeneficiarioTitular().getCpf());
		
		dadosDTO.setDataPreenchimento(dadosSocioeconomicos.getDataPreenchimento());
		dadosDTO.setDataUltimaAtualizacao(dadosSocioeconomicos.getDataUltimaAtualizacao());
		dadosDTO.setPontuacao(dadosSocioeconomicos.getPontuacao());
		
		dadosDTO.setParecer(dadosSocioeconomicos.getParecer());
		
		return dadosDTO;
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Integer id) {
		this.dados.remover(id);
	}
	
	@GetMapping("/dadosDeUmBeneficiario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<DadosSocioEconomicos> recuperarDadosDeUmBeneficiario(@PathVariable Integer idBeneficiario){
		 return this.dados.recuperarDadosDeUmBeneficiario(idBeneficiario);
	 } 

}
