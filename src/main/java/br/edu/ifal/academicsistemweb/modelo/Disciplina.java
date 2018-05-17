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
	private Long id;

	@Column
	private String nome;

	@ManyToOne
	private Professor professor;

	@ManyToMany
	private List<Aluno> alunos;

	@ManyToMany
	private Set<Assunto> assuntos = new HashSet<>();

	

	public Disciplina(Long id, String nome, Professor professor, List<Aluno> alunos, Set<Assunto> assuntos) {
		super();
		this.id = id;
		this.nome = nome;
		this.professor = professor;
		this.alunos = alunos;
		this.assuntos = assuntos;
	}

	public Disciplina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(int i) {
		this.id = (long) i;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
