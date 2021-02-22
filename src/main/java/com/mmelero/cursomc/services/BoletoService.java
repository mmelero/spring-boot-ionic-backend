package com.mmelero.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		//Pegar a data do calendario data atual
		Calendar cal = Calendar.getInstance();
		//atribuir a data do pedido para a varivel cal
		cal.setTime(instanteDoPedido);
		//Adicionar 7 dias na data do pedido;
		cal.add(Calendar.DAY_OF_MONTH, 7);
		//Atribuindo a data do vencimento do pagto
		pagto.setDataVencimento(cal.getTime());
	}
}
