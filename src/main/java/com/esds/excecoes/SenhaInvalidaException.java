package com.esds.excecoes;

public class SenhaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2407132994274789888L;
	
	public SenhaInvalidaException() {
		super("Senha Inválida! ");
	}

}
