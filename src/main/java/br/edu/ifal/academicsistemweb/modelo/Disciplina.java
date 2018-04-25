package br.edu.ifal.academicsistemweb.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String nome;

	@ManyToOne
	private Professor Professor;

	@ManyToMany
	private List<Aluno> alunos;

	@ManyToMany
	private Set<Assunto> assuntos = new HashSet<>();

	

	public Disciplina(Integer id, String nome, Professor professor, List<Aluno> alunos, Set<Assunto> assuntos) {
		super();
		this.id = id;
		this.nome = nome;
		this.Professor = professor;
		this.alunos = alunos;
		this.assuntos = assuntos;
	}

	public Disciplina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return Professor;
	}

	public void setProfessor(Professor professor) {
		Professor = professor;
	}

	public Set<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(Set<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Assunto> getassunto() {
		return assuntos;
	}

	public void setAssunto(Set<Assunto> assunto) {
		this.assuntos = assunto;
	}

}
