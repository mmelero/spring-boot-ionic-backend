package com.mmelero.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Categoria;
import com.mmelero.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	//Decalar dependencia do tip de Objeto Repository
	@Autowired
	//a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private CategoriaRepository repo;
	
	
	public Categoria find(Long id) {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
		}
	
}
