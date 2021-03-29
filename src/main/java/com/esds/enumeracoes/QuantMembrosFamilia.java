package com.esds.enumeracoes;

public enum QuantMembrosFamilia {
	
	//Quantidade de pessoas na família
	
	//UM("1"), DOIS("2"), TRES("3"), QUARTO("4"), ENTRE_CINCO_SETE("6"), ENTRE_OITO_DEZ("8"), MAIS_DE_DEZ("10");
	
	UM("UM"), DOIS("DOIS"), TRES("TRES"), QUATRO("QUATRO"), ENTRE_CINCO_SETE("ENTRE_CINCO_SETE"), 
	ENTRE_OITO_DEZ("ENTRE_OITO_DEZ"), MAIS_DE_DEZ("MAIS_DE_DEZ");
	
	private String valor;
	
	private QuantMembrosFamilia(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}


}
