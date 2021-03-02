package com.mmelero.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mmelero.cursomc.domain.enuns.Perfil;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String senha;
	//lista de perfis
	private Collection<? extends GrantedAuthority> authorities;
	
	//para ter acesso ao Id
	public Long getId() {
		return id;
	}
	
	public UserSS() {
	}
	
	//alterado para receber uma lista de perfis
	public UserSS(Long id, 
				  String email, 
				  String senha, 
				  /*Collection<? extends GrantedAuthority> authorities)*/
				  Set<Perfil> perfis){
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		//converter o conjunto de perfis para a lista
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(Perfil perfil) {
		//verifica se o perfil está na lista de GrantedAuthority
		//necessario converter o perfil do usuaro para lista GrantedAuthority
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
