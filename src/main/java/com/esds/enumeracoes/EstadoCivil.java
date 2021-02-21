package com.esds.enumeracoes;

public enum EstadoCivil {
	
	SOLTEIRO ("SOLTEIRO"), CASADO("CASADO"), DIVORCIADO ("DIVORCIADO"), UNIAO_ESTAVEL("UNIAO_ESTAVEL"), OUTRO("OUTRO");
	
	public String estado;
	
	EstadoCivil(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return this.estado;
	}
}
