package com.esds.modelo;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.esds.enumeracoes.ComposicaoDomicilio;
import com.esds.enumeracoes.CondicaoDaResidencia;
import com.esds.enumeracoes.LocalidadeDomicilio;
import com.esds.enumeracoes.QuanMembrosIdosos;
import com.esds.enumeracoes.QuantComodos;
import com.esds.enumeracoes.QuantMembrosCrianca;
import com.esds.enumeracoes.QuantMembrosFamilia;
import com.esds.enumeracoes.RendaPerCapita;
import com.esds.enumeracoes.TipoDeTrabalho;

@Entity
public class DadosSocioEconomicos {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private boolean temBanheiro;
	private boolean aguaEncanada;
	private boolean energiaEletrica;
	private boolean coletaEsgoto;
	private boolean familiaIndigena;
	private boolean familiaQuilombola;
	private boolean membroEmpregado;
	private boolean doencaCronica;	
	private boolean membroDeficiente;
	
	private ComposicaoDomicilio composicaoDomicilio;
	private LocalidadeDomicilio localidade;
	private CondicaoDaResidencia condicaoResidencia;
	private QuantComodos quantComodos;	
	private QuantMembrosFamilia quantMembrosFamilia;
	private QuanMembrosIdosos quantMembrosIdosos;
	private TipoDeTrabalho tipoDeTrabalho;
	private RendaPerCapita rendaPerCapita;
	private QuantMembrosCrianca quantMembrosCriancas;
	
	private String[] nomesMembrosFamilia;
	private String[] doencasCronicas;
	private float rendaFamiliar;	
	private int quantMembroDeficiente;
	private String tipoDeficiencia;	
	
	private Date dataPreenchimento;
	private Date dataUltimaAtualizacao;
	private float pontuacao;
	
	
	@ManyToOne
	private Beneficiario beneficiarioTitular;

	@Override
	public String toString() {
		return "DadosSocioEconomicos [id=" + id + ", temBanheiro=" + temBanheiro + ", aguaEncanada=" + aguaEncanada
				+ ", energiaEletrica=" + energiaEletrica + ", coletaEsgoto=" + coletaEsgoto + ", familiaIndigena="
				+ familiaIndigena + ", familiaQuilombola=" + familiaQuilombola + ", membroEmpregado=" + membroEmpregado
				+ ", doencaCronica=" + doencaCronica + ", membroDeficiente=" + membroDeficiente
				+ ", composicaoDomicilio=" + composicaoDomicilio + ", localidade=" + localidade
				+ ", condicaoResidencia=" + condicaoResidencia + ", quantComodos=" + quantComodos
				+ ", quantMembrosFamilia=" + quantMembrosFamilia + ", quantMembrosIdosos=" + quantMembrosIdosos
				+ ", tipoDeTrabalho=" + tipoDeTrabalho + ", rendaPerCapita=" + rendaPerCapita
				+ ", quantMembrosCriancas=" + quantMembrosCriancas + ", nomesMembrosFamilia="
				+ Arrays.toString(nomesMembrosFamilia) + ", doencasCronicas=" + Arrays.toString(doencasCronicas)
				+ ", rendaFamiliar=" + rendaFamiliar + ", quantMembroDeficiente=" + quantMembroDeficiente
				+ ", tipoDeficiencia=" + tipoDeficiencia + ", beneficiarioTitular=" + beneficiarioTitular + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isTemBanheiro() {
		return temBanheiro;
	}

	public void setTemBanheiro(boolean temBanheiro) {
		this.temBanheiro = temBanheiro;
	}

	public boolean isAguaEncanada() {
		return aguaEncanada;
	}

	public void setAguaEncanada(boolean aguaEncanada) {
		this.aguaEncanada = aguaEncanada;
	}

	public boolean isEnergiaEletrica() {
		return energiaEletrica;
	}

	public void setEnergiaEletrica(boolean energiaEletrica) {
		this.energiaEletrica = energiaEletrica;
	}

	public boolean isColetaEsgoto() {
		return coletaEsgoto;
	}

	public void setColetaEsgoto(boolean coletaEsgoto) {
		this.coletaEsgoto = coletaEsgoto;
	}

	public boolean isFamiliaIndigena() {
		return familiaIndigena;
	}

	public void setFamiliaIndigena(boolean familiaIndigena) {
		this.familiaIndigena = familiaIndigena;
	}

	public boolean isFamiliaQuilombola() {
		return familiaQuilombola;
	}

	public void setFamiliaQuilombola(boolean familiaQuilombola) {
		this.familiaQuilombola = familiaQuilombola;
	}

	public boolean isMembroEmpregado() {
		return membroEmpregado;
	}

	public void setMembroEmpregado(boolean membroEmpregado) {
		this.membroEmpregado = membroEmpregado;
	}

	public boolean isDoencaCronica() {
		return doencaCronica;
	}

	public void setDoencaCronica(boolean doencaCronica) {
		this.doencaCronica = doencaCronica;
	}

	public boolean isMembroDeficiente() {
		return membroDeficiente;
	}

	public void setMembroDeficiente(boolean membroDeficiente) {
		this.membroDeficiente = membroDeficiente;
	}

	public float getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(float rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public int getQuantMembroDeficiente() {
		return quantMembroDeficiente;
	}

	public void setQuantMembroDeficiente(int quantMembroDeficiente) {
		this.quantMembroDeficiente = quantMembroDeficiente;
	}

	public String getTipoDeficiencia() {
		return tipoDeficiencia;
	}

	public void setTipoDeficiencia(String tipoDeficiencia) {
		this.tipoDeficiencia = tipoDeficiencia;
	}

	public Beneficiario getBeneficiarioTitular() {
		return beneficiarioTitular;
	}

	public void setBeneficiarioTitular(Beneficiario beneficiarioTitular) {
		this.beneficiarioTitular = beneficiarioTitular;
	}

	public ComposicaoDomicilio getComposicaoDomicilio() {
		return composicaoDomicilio;
	}

	public void setComposicaoDomicilio(ComposicaoDomicilio composicaoDomicilio) {
		this.composicaoDomicilio = composicaoDomicilio;
	}

	public LocalidadeDomicilio getLocalidade() {
		return localidade;
	}

	public void setLocalidade(LocalidadeDomicilio localidade) {
		this.localidade = localidade;
	}

	public CondicaoDaResidencia getCondicaoResidencia() {
		return condicaoResidencia;
	}

	public void setCondicaoResidencia(CondicaoDaResidencia condicaoResidencia) {
		this.condicaoResidencia = condicaoResidencia;
	}

	public QuantComodos getQuantComodos() {
		return quantComodos;
	}

	public void setQuantComodos(QuantComodos quantComodos) {
		this.quantComodos = quantComodos;
	}

	public QuantMembrosFamilia getQuantMembrosFamilia() {
		return quantMembrosFamilia;
	}

	public void setQuantMembrosFamilia(QuantMembrosFamilia quantMembrosFamilia) {
		this.quantMembrosFamilia = quantMembrosFamilia;
	}

	public QuanMembrosIdosos getQuantMembrosIdosos() {
		return quantMembrosIdosos;
	}

	public void setQuantMembrosIdosos(QuanMembrosIdosos quantMembrosIdosos) {
		this.quantMembrosIdosos = quantMembrosIdosos;
	}

	public TipoDeTrabalho getTipoDeTrabalho() {
		return tipoDeTrabalho;
	}

	public void setTipoDeTrabalho(TipoDeTrabalho tipoDeTrabalho) {
		this.tipoDeTrabalho = tipoDeTrabalho;
	}

	public RendaPerCapita getRendaPerCapita() {
		return rendaPerCapita;
	}

	public void setRendaPerCapita(RendaPerCapita rendaPerCapita) {
		this.rendaPerCapita = rendaPerCapita;
	}

	public QuantMembrosCrianca getQuantMembrosCriancas() {
		return quantMembrosCriancas;
	}

	public void setQuantMembrosCriancas(QuantMembrosCrianca quantMembrosCriancas) {
		this.quantMembrosCriancas = quantMembrosCriancas;
	}

	public String[] getNomesMembrosFamilia() {
		return nomesMembrosFamilia;
	}

	public void setNomesMembrosFamilia(String[] nomesMembrosFamilia) {
		this.nomesMembrosFamilia = nomesMembrosFamilia;
	}

	public String[] getDoencasCronicas() {
		return doencasCronicas;
	}

	public void setDoencasCronicas(String[] doencasCronicas) {
		this.doencasCronicas = doencasCronicas;
	}

	public Date getDataPreenchimento() {
		return dataPreenchimento;
	}

	public void setDataPreenchimento(Date dataPreenchimento) {
		this.dataPreenchimento = dataPreenchimento;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	
}
