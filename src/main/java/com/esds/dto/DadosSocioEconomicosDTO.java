package com.esds.dto;

import java.util.Arrays;
import java.util.Date;

public class DadosSocioEconomicosDTO {
	
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
	
	private String composicaoDomicilio;
	private String localidade;
	private String condicaoResidencia;
	private String quantComodos;	
	private String quantMembrosFamilia;
	private String quantMembrosIdosos;
	private String tipoDeTrabalho;
	private String rendaPerCapita;
	private String quantMembrosCriancas;
	
	private String[] nomesMembrosFamilia;
	private String[] doencasCronicas;
	private float rendaFamiliar;	
	private int quantMembroDeficiente;
	private String tipoDeficiencia;		
	
	private String idBeneficiario;

	private Date dataPreenchimento;
	private Date dataUltimaAtualizacao;
	private float pontuacao;
	
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DadosSocioEconomicosDTO [id=" + id + ", temBanheiro=" + temBanheiro + ", aguaEncanada=" + aguaEncanada
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
				+ ", tipoDeficiencia=" + tipoDeficiencia + ", idBeneficiario=" + idBeneficiario + "]";
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

	public String getComposicaoDomicilio() {
		return composicaoDomicilio;
	}

	public void setComposicaoDomicilio(String composicaoDomicilio) {
		this.composicaoDomicilio = composicaoDomicilio;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getCondicaoResidencia() {
		return condicaoResidencia;
	}

	public void setCondicaoResidencia(String condicaoResidencia) {
		this.condicaoResidencia = condicaoResidencia;
	}

	public String getQuantComodos() {
		return quantComodos;
	}

	public void setQuantComodos(String quantComodos) {
		this.quantComodos = quantComodos;
	}

	public String getQuantMembrosFamilia() {
		return quantMembrosFamilia;
	}

	public void setQuantMembrosFamilia(String quantMembrosFamilia) {
		this.quantMembrosFamilia = quantMembrosFamilia;
	}

	public String getQuantMembrosIdosos() {
		return quantMembrosIdosos;
	}

	public void setQuantMembrosIdosos(String quantMembrosIdosos) {
		this.quantMembrosIdosos = quantMembrosIdosos;
	}

	public String getTipoDeTrabalho() {
		return tipoDeTrabalho;
	}

	public void setTipoDeTrabalho(String tipoDeTrabalho) {
		this.tipoDeTrabalho = tipoDeTrabalho;
	}

	public String getRendaPerCapita() {
		return rendaPerCapita;
	}

	public void setRendaPerCapita(String rendaPerCapita) {
		this.rendaPerCapita = rendaPerCapita;
	}

	public String getQuantMembrosCriancas() {
		return quantMembrosCriancas;
	}

	public void setQuantMembrosCriancas(String quantMembrosCriancas) {
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

	public String getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
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
