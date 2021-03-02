package com.mmelero.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmelero.cursomc.domain.Categoria;
import com.mmelero.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat Where obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
	@Param("nome") String nome,
	@Param("categorias") List<Categoria> categorias,
	Pageable pageRequest);
}
