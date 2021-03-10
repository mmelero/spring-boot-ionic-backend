package com.mmelero.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mmelero.cursomc.domain.Cidade;
import com.mmelero.cursomc.dto.CidadeDTO;
import com.mmelero.cursomc.repositories.CidadeRepository;
import com.mmelero.cursomc.services.exceptions.DataIntegrityException;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	// Decalar dependencia do tip de Objeto Repository
	@Autowired
	// a notação autowired torna a dependecia automaticamente estanciada pelo Spring
	private CidadeRepository repo;
	
	public Cidade find(Long id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}


	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public List<Cidade> findByEstado(Long estadoId){
		return repo.findCidadesByEstadoId(estadoId);
	}

	public Cidade update(Cidade obj) {
		// Estanciar um cliente a partir do banco de dados
		Cidade newObj = find(obj.getId());
		
		//Metodo auxiliar para atualizar os dados com base no banco de dados.
		updateData(newObj, obj );
		return repo.save(newObj);

	}
	
	public void delete(Long id) {

		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Cidade com Cidades");

		}
	}
	
	//função para criar paginação
	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Função auxiliar para validação do dto
	public Cidade fromDTO(CidadeDTO objDto) {
		return new Cidade(objDto.getId(), objDto.getNome(), objDto.getEstado());
	}
	
	private void updateData(Cidade newObj, Cidade obj) {
		newObj.setNome(obj.getNome());
		
	}

}
