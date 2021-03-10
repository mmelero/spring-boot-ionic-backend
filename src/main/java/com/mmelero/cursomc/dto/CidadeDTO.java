package com.mmelero.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.mmelero.cursomc.domain.Cidade;
import com.mmelero.cursomc.domain.Estado;

public class CidadeDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	//Notação para validação de atributos - não foi possivel utilizar o hibernate, pois se encontra
	//depriciado, no caso foi utilizado a notação do javax
//	@NotBlank(message="Nome não pode ser vazio!!!")
	@NotBlank(message="Nome não pode ser vazio!!!")
	@Length(min=5, max=80, message = "Nome deve ter entre 5 a 80 caracteres!!!")
	private String nome;
	private Estado estado;
	
	public CidadeDTO() {
	}

	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
		estado = obj.getEstado();
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

	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
