package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{

}
