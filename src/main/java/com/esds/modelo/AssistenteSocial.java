package com.esds.modelo;

import javax.persistence.Entity;

@Entity
public class AssistenteSocial extends Funcionario{
	
	private String matriculaCFESS;

	public String getMatriculaCFESS() {
		return matriculaCFESS;
	}

	public void setMatriculaCFESS(String matriculaCFESS) {
		this.matriculaCFESS = matriculaCFESS;
	}

	

}
