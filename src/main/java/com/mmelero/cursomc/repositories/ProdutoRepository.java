package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
