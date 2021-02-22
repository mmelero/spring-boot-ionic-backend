package com.mmelero.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Categoria;
import com.mmelero.cursomc.domain.Produto;
import com.mmelero.cursomc.repositories.CategoriaRepository;
import com.mmelero.cursomc.repositories.ProdutoRepository;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository; 
	          
	public Produto find(Long id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}

}
