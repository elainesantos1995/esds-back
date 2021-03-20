package com.esds.dto;

import java.util.Date;

import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsoDeBeneficioTDO {
	
	private Integer id;
	private Date dataDoUso;
	private boolean controleBiometria;
	private boolean controleDocumento;
	private boolean controleCarteirinha;
	
	@JsonIgnore
	private Inscricao inscricao;
	
	@JsonIgnore
	private Beneficio beneficio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataDoUso() {
		return dataDoUso;
	}

	public void setDataDoUso(Date dataDoUso) {
		this.dataDoUso = dataDoUso;
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

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}
	
	
}
