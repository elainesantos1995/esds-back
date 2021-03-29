package com.esds.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class UsoDeBeneficio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataDoUso;
	private boolean controleBiometria;
	private boolean controleDocumento;
	private boolean controleCarteirinha;
	
	@ManyToOne
	private Inscricao inscricao;
	
	@OneToOne  
	private Beneficio beneficio;
	
	public UsoDeBeneficio(){
		
	}	
	
	@Override
	public String toString() {
		return "UsoDeBeneficio [id=" + id + ", dataDoUso=" + dataDoUso + ", controleBiometria=" + controleBiometria
				+ ", controleDocumento=" + controleDocumento + ", controleCarteirinha=" + controleCarteirinha
				+ ", inscricao=" + inscricao + ", beneficio=" + beneficio + "]";
	}



	public Integer getId() {
		return this.id;
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
