package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Long>{

}
