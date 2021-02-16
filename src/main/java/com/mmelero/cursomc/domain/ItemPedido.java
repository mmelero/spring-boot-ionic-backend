package com.mmelero.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;

	//iginorar no caso de serialização, nem o pedido e nem o produto
	@JsonIgnore
	//no jpa tendo um atributo outra classe com uma chave composta
	// é necessario ir na classe de referencia(ItemPedidoPK), no caso pedido e colocar 
	// a notação: @Embeddable, esta notação informa que a classe será um subtipo.
	//Qdo a chave é composta, devemos usar a notação @EmbeddedId para identificar a chave,
	//qdo a chave é simples e da mesma classe usua-se a notação @Id
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		
	}

	//No construtor ItemPedido, é necessario trocar os atributo ItemPedidoPK(Itemproduto+produto)
	//chave composta do itemprodutoPK, faz sentido apenas para o jpa
	//public ItemPedido(ItemPedidoPK id, Double desconto, Integer quantidade, Double preco) {
	public ItemPedido(Pedido pedido, Produto produto,  Double desconto, Integer quantidade, Double preco) {
		super();
//		this.id = id;
		// para atribuir a chave dupla no ai, efetuas as operações:
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	//Para ter acesso ao pedido, fora da classe itemPedido
	@JsonIgnore
	public Pedido getpedido() {
		return id.getPedido();
	}

	//Para ter acesso ao produto, fora da classe itemPedido
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
