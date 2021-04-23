package com.esds.enumeracoes;

public enum LocalidadeDomicilio {
	
	URBANA("URBANA"), RURAL("RURAL");
	
	private String valor;
	
	private LocalidadeDomicilio(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
