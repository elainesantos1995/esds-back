package com.esds.dto;

import java.util.Date;
import java.util.List;

import com.esds.modelo.Beneficio;

public class ProgramaDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Date vigenciaInicio;
	private Date vigenciaTermino;
	private List<Beneficio> beneficios;
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

}
