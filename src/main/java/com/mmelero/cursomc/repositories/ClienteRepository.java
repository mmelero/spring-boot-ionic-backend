package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

	//Pesquisa no banco de dados clientes com e-mail repassado
	//Função do springData - framework
	Cliente findByEmail(String email);

}
