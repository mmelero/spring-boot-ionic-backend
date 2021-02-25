package com.mmelero.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.mmelero.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
