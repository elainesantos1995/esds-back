package com.esds.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.esds.enumeracoes.StatusInscricao;

@Entity
public class Inscricao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataInscricao;
	private String parecer;
	private StatusInscricao status;
	
	private int quantBeneficiosASeremRetiradados;
	private int quantBeneficiosRetirados;
	
	@ManyToOne
	private Beneficiario beneficiario;

	@OneToOne
	private Beneficio beneficio;	
	
	public Inscricao() {
		
	}
	
	@Override
	public String toString() {
		return "Inscricao [id=" + id + ", dataInscricao=" + dataInscricao + ", parecer=" + parecer + ", status="
				+ status + ",  quantBeneficiosASeremRetiradados="
				+ quantBeneficiosASeremRetiradados + ", quantBeneficiosRetirados=" + quantBeneficiosRetirados
				+ ", beneficiario=" + beneficiario + ", benefios=" + beneficio + "]";
	}



	public Inscricao(Integer id, Date dataInscricao, String parecer, StatusInscricao status) {
		super();
		this.id = id;
		this.dataInscricao = dataInscricao;
		this.parecer = parecer;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public StatusInscricao getStatus() {
		return status;
	}

	public void setStatus(StatusInscricao status) {
		this.status = status;
	}

	public int getQuantBeneficiosASeremRetiradados() {
		return quantBeneficiosASeremRetiradados;
	}

	public void setQuantBeneficiosASeremRetiradados(int quantBeneficiosASeremRetiradados) {
		this.quantBeneficiosASeremRetiradados = quantBeneficiosASeremRetiradados;
	}

	public int getQuantBeneficiosRetirados() {
		return quantBeneficiosRetirados;
	}

	public void setQuantBeneficiosRetirados(int quantBeneficiosRetirados) {
		this.quantBeneficiosRetirados = quantBeneficiosRetirados;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

//	public List<Beneficio> getBenefios() {
//		return benefios;
//	}
//
//	public void setBenefios(List<Beneficio> benefios) {
//		this.benefios = benefios;
//	}
//	
	
	
	

}
