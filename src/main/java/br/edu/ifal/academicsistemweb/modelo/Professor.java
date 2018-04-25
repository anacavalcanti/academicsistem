package br.edu.ifal.academicsistemweb.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Professor")
public class Professor extends Pessoa {

	@Column
	private String formacao;

	@Column
	private String cpf;

	@Column(name = "tipo_professor", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoProfessor tipoProfessor;

	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Professor(int id, String nome, String formacao, String cpf, TipoProfessor tipoProfessor) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.formacao = formacao;
		this.cpf = cpf;
		this.tipoProfessor = tipoProfessor;
	}

	public TipoProfessor getTipoProfessor() {
		return tipoProfessor;
	}

	public void setTipoProfessor(TipoProfessor tipoProfessor) {
		this.tipoProfessor = tipoProfessor;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
