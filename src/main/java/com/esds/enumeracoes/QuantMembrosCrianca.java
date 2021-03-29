package com.esds.enumeracoes;

public enum QuantMembrosCrianca {
	
	//UM("5"), DOIS("7"), TRES("9"), MAIS_DE_TRES("11");
	
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
