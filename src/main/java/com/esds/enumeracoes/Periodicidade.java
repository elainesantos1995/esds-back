package com.esds.enumeracoes;

public enum Periodicidade {
	
	DIARIA("DIARIA"), SEMANAL("SEMANAL"), QUINZENAL("QUINZENAL"), MENSAL("MENSAL"), BIMESTRAL("BIMESTRAL"), TRIMESTRAL("TRIMESTRAL"), SAZONAL("SAZONAL");
	
	public String periodo;
	
	Periodicidade(String periodicidade){
		this.periodo = periodicidade;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	

}
