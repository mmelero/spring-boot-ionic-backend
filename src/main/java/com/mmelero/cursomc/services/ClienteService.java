package com.mmelero.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.dto.ClienteDTO;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.services.exceptions.DataIntegrityException;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private    ClienteRepository repo;

	public Cliente find(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " +  Cliente.class.getName()));
	}
	

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		// Estanciar um cliente a partir do banco de dados
		Cliente newObj = find(obj.getId());
		
		//Metodo auxiliar para atualizar os dados com base no banco de dados.
		updateData(newObj, obj );
		return repo.save(newObj);

	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}


	public void delete(Long id) {

		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas!!");

		}
	}
	
	public List<Cliente> findAll(){
		
		return repo.findAll();
		
	}
	
	//função para criar paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Função auxiliar para validação do dto
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}


}
