package com.mmelero.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mmelero.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			//retorna o usuario que está logado no sistema
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
	}

}
