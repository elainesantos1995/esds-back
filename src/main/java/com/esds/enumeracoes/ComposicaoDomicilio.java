package com.esds.enumeracoes;

public enum ComposicaoDomicilio {
	
	//Do que a residencia é feita
	//ALVENARIA("6"), MADEIRA("7"), TAIPA("7"), SITUACAO_DE_RUA("10");
	
	ALVENARIA("ALVENARIA"), MADEIRA("MADEIRA"), TAIPA("TAIPA"), SITUACAO_DE_RUA("SITUACAO_DE_RUA");
	
	private String valor;
	
	private ComposicaoDomicilio(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	

}
