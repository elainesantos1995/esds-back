package com.esds.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProgramaSocial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private Date vigenciaInicio;
	private Date vigenciaTermino;
	private Integer ano;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "programa", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Beneficio> beneficios;
	
	public ProgramaSocial() {
	}
	
	@Override
	public String toString() {
		return "ProgramaSocial [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", vigenciaInicio="
				+ vigenciaInicio + ", vigenciaTermino=" + vigenciaTermino + ", ano=" + ano + ", beneficios="
				+ beneficios + "]";
	}



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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getVigenciaInicio() {
		return vigenciaInicio;
	}
	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
	public Date getVigenciaTermino() {
		return vigenciaTermino;
	}
	public void setVigenciaTermino(Date vigenciaTermino) {
		this.vigenciaTermino = vigenciaTermino;
	}
	public List<Beneficio> getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
}
