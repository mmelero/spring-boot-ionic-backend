package com.mmelero.cursomc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Estado;

@Transactional
public interface EstadoRepository  extends JpaRepository<Estado, Long>{

	@Transactional
	public List<Estado>findAllByOrderByNome();
}
