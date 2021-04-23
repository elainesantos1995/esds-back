package com.esds.enumeracoes;

public enum ComposicaoDomicilio {
	
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
