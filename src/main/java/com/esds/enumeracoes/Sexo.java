package com.esds.enumeracoes;

public enum Sexo {
	
	FEMININO("FEMININO"), MASCULINO("MASCULINO"), TRANSEXUAL("TRANSEXUAL"), TRAVESTI ("TRAVESTI"), OUTRO("OUTRO");
	
	public String opcao;
	
	Sexo(String opcao){
		this.opcao = opcao;
	}

	public String getOpcao() {
		return this.opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	
	
}
