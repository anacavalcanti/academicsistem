package br.edu.ifal.academicsistemweb.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_assunto")
public class Assunto {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length =60, nullable = false)
	private String nome;
	
	
	public int getid() {
		return id;
	}
	public void setid(Integer id){
		this.id=id;
	}
	
	
	public String getnome() {
		return nome;
	}
	public void setnome(String nome) {
		this.nome = nome;
	}
	
}
