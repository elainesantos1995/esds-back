package com.esds.enumeracoes;

public enum StatusInscricao {
	
	EM_ANALISE("EM_ANALISE"), DEFERIDA("DEFERIDA"), INDEFERIDA("INDEFERIDA"), EM_LISTA("EM_LISTA"),
	INADIMPLENTE("INADIMPLENTE"), CONCEDIDO("CONCEDIDO"), CANCELADO("CANCELADO");
	
	private String status;
	
	StatusInscricao(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
