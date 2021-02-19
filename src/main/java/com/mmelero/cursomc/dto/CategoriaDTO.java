package com.mmelero.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.mmelero.cursomc.domain.Categoria;

public class CategoriaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	//Notação para validação de atributos - não foi possivel utilizar o hibernate, pois se encontra
	//depriciado, no caso foi utilizado a notação do javax
//	@NotBlank(message="Nome não pode ser vazio!!!")
	@NotBlank(message="Nome não pode ser vazio!!!")
	@Length(min=5, max=80, message = "Nome deve ter entre 5 a 80 caracteres!!!")
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
