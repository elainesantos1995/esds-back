package com.esds.enumeracoes;

public enum QuantMembrosCrianca {
	
	ZERO("ZERO"), UM("UM"), DOIS("DOIS"), TRES("TRES"), MAIS_DE_TRES("MAIS_DE_TRES");
	
	private String valor;
	
	private QuantMembrosCrianca(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
