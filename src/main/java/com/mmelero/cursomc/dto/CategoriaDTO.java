package com.mmelero.cursomc.dto;

import java.io.Serializable;

import com.mmelero.cursomc.domain.Categoria;

public class CategoriaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
