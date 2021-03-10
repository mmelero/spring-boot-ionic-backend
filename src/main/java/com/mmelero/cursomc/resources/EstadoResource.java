package com.mmelero.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmelero.cursomc.domain.Cidade;
import com.mmelero.cursomc.domain.Estado;
import com.mmelero.cursomc.dto.CidadeDTO;
import com.mmelero.cursomc.dto.EstadoDTO;
import com.mmelero.cursomc.services.CidadeService;
import com.mmelero.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Long id) {
	
		Estado obj = service.find(id);
		return ResponseEntity.ok(obj);
	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EstadoDTO objDto){
		Estado obj = service.fromDTO(objDto);
		obj.setId(null);
		obj = service.insert(obj);
		//linha de comando para pegar o id que foi digitado para incluir - metodo post
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EstadoDTO objDto, @PathVariable Long id){
		Estado obj = service.fromDTO(objDto);
		obj.setId(id);
		find(obj.getId());
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		//Busca a lista de categorias do banco
		List<Estado> list = service.findAll();
		//Converter a lista para listaDto
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Long estadoId) {
		//Busca a lista de categorias do banco
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		//Converter a lista para listaDto
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@RequestMapping(value = "/page",method = RequestMethod.GET)
	public ResponseEntity<Page<EstadoDTO>> findPge(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) 
	{
		Page<Estado> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Converter a lista para listaDto
		Page<EstadoDTO> listDto = list.map(obj -> new EstadoDTO(obj));
		return ResponseEntity.ok(listDto);
	
	}

}
