package com.mmelero.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Estado;
import com.mmelero.cursomc.dto.EstadoDTO;
import com.mmelero.cursomc.repositories.EstadoRepository;
import com.mmelero.cursomc.services.exceptions.DataIntegrityException;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private EstadoRepository repo;

	public Estado find(Long id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}


	public Estado insert(Estado obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Estado update(Estado obj) {
		// Estanciar um cliente a partir do banco de dados
		Estado newObj = find(obj.getId());
		
		//Metodo auxiliar para atualizar os dados com base no banco de dados.
		updateData(newObj, obj );
		return repo.save(newObj);

	}
	
	public void delete(Long id) {

		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Estado com Cidades");

		}
	}
	
	public List<Estado> findAll(){
		
		return repo.findAllByOrderByNome();
		
	}
	
	
	
	//função para criar paginação
	public Page<Estado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Função auxiliar para validação do dto
	public Estado fromDTO(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getNome(), objDto.getSigla());
	}
	
	private void updateData(Estado newObj, Estado obj) {
		newObj.setNome(obj.getNome());
		newObj.setSigla(obj.getSigla());
		
	}

}
