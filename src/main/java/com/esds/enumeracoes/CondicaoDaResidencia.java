package com.esds.enumeracoes;

public enum CondicaoDaResidencia {
	
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
