package com.mmelero.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Pedido;
import com.mmelero.cursomc.repositories.PedidoRepository;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private PedidoRepository repo;

	public Pedido find(Long id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
