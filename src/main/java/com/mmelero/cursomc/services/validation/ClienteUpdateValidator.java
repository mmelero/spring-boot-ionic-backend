package com.mmelero.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.dto.ClienteDTO;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	//Obter o parametro da uri - no caso o id do cliente
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		// acessar uma coleção de dados(URL- requisição) para acessar o id do cliente
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Double uriId = Double.parseDouble(map.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já existente!!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}