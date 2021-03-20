package com.esds.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.esds.enumeracoes.StatusInscricao;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Beneficio;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class InscricaoDTO {
	
	private Integer id;
	private Date dataInscricao;
	private String parecer;
	private String status;
	
	private int quantBeneficiosASeremRetiradados;
	private int quantBeneficiosRetirados;
	
//	@ReadOnlyProperty
//	@JsonIgnore
	private Beneficiario beneficiario;
	
//	@ReadOnlyProperty
//	@JsonIgnore
	private List<Beneficio> beneficios;
	
	private Beneficio beneficio;
	
	private String cpfBeneficiario;
	
	private Integer idBeneficio;

	@Override
	public String toString() {
		return "InscricaoDTO [id=" + id + ", dataInscricao=" + dataInscricao + ", parecer=" + parecer + ", status="
				+ status + ", quantBeneficiosASeremRetiradados="
				+ quantBeneficiosASeremRetiradados + ", quantBeneficiosRetirados=" + quantBeneficiosRetirados
				+ ", beneficiario=" + beneficiario + ", beneficios=" + beneficios + ", cpfBeneficiario="
				+ cpfBeneficiario + "]";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public String getCpfBeneficiario() {
		return cpfBeneficiario;
	}

	public void setCpfBeneficiario(String cpfBeneficiario) {
		this.cpfBeneficiario = cpfBeneficiario;
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	public Integer getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(Integer idBeneficio) {
		this.idBeneficio = idBeneficio;
	}
	
	
	
	
}
