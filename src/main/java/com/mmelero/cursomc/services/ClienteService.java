package com.mmelero.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private    ClienteRepository repo;

	public Cliente find(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " +  Cliente.class.getName()));
	}

}
