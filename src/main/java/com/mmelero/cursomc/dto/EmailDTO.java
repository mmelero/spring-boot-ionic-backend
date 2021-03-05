package com.mmelero.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDTO  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Nome não pode ser vazio!!!")
	@Email(message="Email invalido!!!")
	private String email;
	
	public EmailDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
