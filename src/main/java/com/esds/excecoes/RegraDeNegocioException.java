package com.esds.excecoes;

public class RegraDeNegocioException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4632473270079225504L;
	
	public RegraDeNegocioException(String erro) {
		super(erro);
	}

}
