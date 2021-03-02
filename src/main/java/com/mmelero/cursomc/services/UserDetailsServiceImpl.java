package com.mmelero.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.security.UserSS;

//Implementação do UserDetailsService
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
//		System.out.println("dados do Cliente: "+"\n"+
//						   "Id_Cliente : " +cli.getId()+"\n"+ 
//						   "Email cliente: "+cli.getEmail()+"\n"+ 
//						   "senha: "+cli.getSenha()+"\n"+ 
//						   "Perfil: "+cli.getPerfis());
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
