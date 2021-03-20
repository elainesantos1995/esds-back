package com.esds.enumeracoes;

public enum RendaPerCapita {
	
	//MENOR_UM_SM("10"), IGUAL_UM_SM("5"), ATE_UM_SM_E_MEIO("4"), ENTRE_UM_E_TRES_SM("3"), ENTRE_TRES_E_CINCO_SM("2"), MAIOR_CINCO_SM("1");
	
	MENOR_UM_SM("MENOR_UM_SM"), IGUAL_UM_SM("IGUAL_UM_SM"), ATE_UM_SM_E_MEIO("ATE_UM_SM_E_MEIO"), 
	ENTRE_UM_E_TRES_SM("ENTRE_UM_E_TRES_SM"), ENTRE_TRES_E_CINCO_SM("ENTRE_TRES_E_CINCO_SM"), MAIOR_CINCO_SM("MAIOR_CINCO_SM");
	
	private String valor;
	
	private RendaPerCapita(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
