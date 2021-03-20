package com.esds.enumeracoes;

public enum LocalidadeDomicilio {
	
	//Zona
	//URBANA("8"), RURAL("10");
	
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
