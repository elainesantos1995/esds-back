package com.esds.enumeracoes;

public enum QuantComodos {
	
	//Quantidade de cômodos residência
	//UM("10"), DOIS("8"), ENTRE_TRES_E_CINCO("7"), ENTRE_SEIS_E_DEZ("5"), MAIS_DE_DEZ("3");
	
	UM("UM"), DOIS("DOIS"), ENTRE_TRES_E_CINCO("ENTRE_TRES_E_CINCO"), ENTRE_SEIS_E_DEZ("ENTRE_SEIS_E_DEZ"),
	MAIS_DE_DEZ("MAIS_DE_DEZ");
	
	private String valor;
	
	private QuantComodos(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
