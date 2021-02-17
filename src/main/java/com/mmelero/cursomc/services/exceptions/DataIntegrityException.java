package com.mmelero.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg){
		super(msg);
	}
	
	//Sobrecarga da exceção
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
