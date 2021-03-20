package com.esds.enumeracoes;

public enum CondicaoDaResidencia {
	
	//Moradia própria o alugada
	//SITUACAO_DE_RUA("5"), ALUGADA("4"), CEDIDA("3"), PROPRIA_QUITADA("2"), PROPRIA_FINANCIADA("1"); 
	
	SITUACAO_DE_RUA("SITUACAO_DE_RUA"), ALUGADA("ALUGADA"), CEDIDA("CEDIDA"),
	PROPRIA_QUITADA("PROPRIA_QUITADA"), PROPRIA_FINANCIADA("PROPRIA_FINANCIADA"); 
	
	private String valor;
	
	private CondicaoDaResidencia(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
