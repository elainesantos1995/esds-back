package com.esds.dto;

import com.esds.enumeracoes.Periodicidade;
import com.esds.modelo.ProgramaSocial;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.ReadOnlyProperty;

public class BeneficioDTO {
	
	private Integer id;
	private String nome;
	private String justificativa;
	private float totalRecursosAportados;
	private int limiteVagas;
	private boolean controleBiometria;
	private boolean controleDocumento;
	private boolean controleCarteirinha;
	private String periodicidade;
	private int toleranciaUsosInadimplente;
	private int toleranciaUsosCancelado;
	private Integer idPrograma;
	
	@ReadOnlyProperty
	@JsonIgnore
	private ProgramaSocial programa;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public float getTotalRecursosAportados() {
		return totalRecursosAportados;
	}
	public void setTotalRecursosAportados(float totalRecursosAportados) {
		this.totalRecursosAportados = totalRecursosAportados;
	}
	public int getLimiteVagas() {
		return limiteVagas;
	}
	public void setLimiteVagas(int limiteVagas) {
		this.limiteVagas = limiteVagas;
	}
	public boolean isControleBiometria() {
		return controleBiometria;
	}
	public void setControleBiometria(boolean controleBiometria) {
		this.controleBiometria = controleBiometria;
	}
	public boolean isControleDocumento() {
		return controleDocumento;
	}
	public void setControleDocumento(boolean controleDocumento) {
		this.controleDocumento = controleDocumento;
	}
	public boolean isControleCarteirinha() {
		return controleCarteirinha;
	}
	public void setControleCarteirinha(boolean controleCarteirinha) {
		this.controleCarteirinha = controleCarteirinha;
	}
	public String getPeriodicidade() {
		return periodicidade;
	}
	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}
	public int getToleranciaUsosInadimplente() {
		return toleranciaUsosInadimplente;
	}
	public void setToleranciaUsosInadimplente(int toleranciaUsosInadimplente) {
		this.toleranciaUsosInadimplente = toleranciaUsosInadimplente;
	}
	public int getToleranciaUsosCancelado() {
		return toleranciaUsosCancelado;
	}
	public void setToleranciaUsosCancelado(int toleranciaUsosCancelado) {
		this.toleranciaUsosCancelado = toleranciaUsosCancelado;
	}
	public ProgramaSocial getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaSocial programa) {
		this.programa = programa;
	}
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	

}
