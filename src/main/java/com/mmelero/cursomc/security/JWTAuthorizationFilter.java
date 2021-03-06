package com.mmelero.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService ) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		//busca a informação do usuario no bco de dados - email
		this.userDetailsService = userDetailsService;
	}

	//metodo para itercepitar a requisção e verificar se usuario está autorizado
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain) throws IOException, ServletException {
/*		String header = null;
		header = request.getHeader("Authorization");
*/		
		if((request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer "))) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, request.getHeader("Authorization").substring(7));
			if(auth != null) {
				//liberar a autenticação
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
		
	}
	
	//gerar o objeto UsernamePasswordAuthenticationToken a partir do token
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		
		return null;
	}
	
}
