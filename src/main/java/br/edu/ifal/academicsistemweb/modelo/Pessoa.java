package br.edu.ifal.academicsistemweb.modelo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Pessoa {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Embedded
	private Endereco endereco;
	
	public Pessoa() {
		super();
	}
	public Pessoa(Integer id, String nome, Endereco Endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = Endereco;
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
