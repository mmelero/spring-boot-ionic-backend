package com.mmelero.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

//classe auxiliar conterá todos os possiveis erros da super classe StandardError
// e um lista de de erros implementadas na classe
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName,message));
	}

	
}
