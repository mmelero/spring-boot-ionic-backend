package com.mmelero.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Pedido;

public interface PedidoRepository  extends JpaRepository<Pedido, Long>{

}
