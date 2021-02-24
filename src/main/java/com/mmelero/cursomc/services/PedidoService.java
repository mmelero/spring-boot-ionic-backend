package com.mmelero.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.ItemPedido;
import com.mmelero.cursomc.domain.PagamentoComBoleto;
import com.mmelero.cursomc.domain.Pedido;
import com.mmelero.cursomc.domain.enuns.EstadoPagamento;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.repositories.ItemPedidoRepository;
import com.mmelero.cursomc.repositories.PagamentoRepository;
import com.mmelero.cursomc.repositories.PedidoRepository;
import com.mmelero.cursomc.repositories.ProdutoRepository;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private PedidoRepository repo;
	
	@Autowired
	BoletoService boletoService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ClienteService clienteService;

	public Pedido find(Long id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		//Grava o pedido no bco de dados
		obj = repo.save(obj);
		
		//gravando o tipo de pagto do pedido no bco de dados
		pagamentoRepository.save(obj.getPagamento());
		//Pegando os itens do pedido / pesquisa de produto e valor do de cada item.
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		//gravando os itens do pedido no bco de dados
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
	
	
}
