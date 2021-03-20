package com.esds.enumeracoes;

public enum QuanMembrosIdosos {
	
	//UM("2"), DOIS("4"), TRES("6"), MAIS_DE_TRES("8");
	
	UM("UM"), DOIS("DOIS"), TRES("TRES"), MAIS_DE_TRES("MAIS_DE_TRES");
	
	private String valor;
	
	private QuanMembrosIdosos(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
