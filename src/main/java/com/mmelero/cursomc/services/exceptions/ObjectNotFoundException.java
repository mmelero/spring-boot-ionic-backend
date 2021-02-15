package com.mmelero.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg){
		super(msg);
	}
	
	//Sobrecarga da exceção
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
