package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Pagamento;

public interface PagamentoRepository  extends JpaRepository<Pagamento, Long>{

}
