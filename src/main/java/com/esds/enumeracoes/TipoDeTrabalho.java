package com.esds.enumeracoes;

public enum TipoDeTrabalho {
	
	DESEMPREGADO("DESEMPREGADO"), INFORMAL("INFORMAL"), TEMPORARIO_RURAL("TEMPORARIO_RURAL"), AUTONOMO("AUTONOMO"), 
	CARTEIRA_ASSINADA("CARTEIRA_ASSINADA"), NAO_REMUNERADO("NAO_REMUNERADO"), 
	FUNCIONARIO_PUBLICO("FUNCIONARIO_PUBLICO"), EMPREGADOR("EMPREGADOR"); 
	
	private String valor;
	
	private TipoDeTrabalho(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
