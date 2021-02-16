package com.mmelero.cursomc.domain;

import javax.persistence.Entity;

import com.mmelero.cursomc.domain.enuns.EstadoPagamento;

//Para as classes que heram de outras classes (Pagamento), basta apenas 
//colocar a notação @Entity, pois o relacionamento já foi efetuado na classe
//Pagamento.
@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}
	

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	

	
}
