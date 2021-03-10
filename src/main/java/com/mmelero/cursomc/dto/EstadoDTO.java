package com.mmelero.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.mmelero.cursomc.domain.Estado;

public class EstadoDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	//Notação para validação de atributos - não foi possivel utilizar o hibernate, pois se encontra
	//depriciado, no caso foi utilizado a notação do javax
//	@NotBlank(message="Nome não pode ser vazio!!!")
	@NotBlank(message="Nome não pode ser vazio!!!")
	@Length(min=5, max=80, message = "Nome deve ter entre 5 a 80 caracteres!!!")
	private String nome;
	private String sigla;
	
	public EstadoDTO() {
	}

	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
		setSigla(obj.getSigla());
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}
