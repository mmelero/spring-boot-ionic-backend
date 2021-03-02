package com.mmelero.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.domain.Pedido;

@Transactional(readOnly=true)
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Transactional
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}