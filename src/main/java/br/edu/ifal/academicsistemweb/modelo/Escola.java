package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "escola")
public class Escola {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String nome;

	@OneToMany
	private List<Aluno> alunos;

	@ManyToMany
	private List<Curso> curso;

	@ManyToMany
	private List<Professor> professores;

	public Escola(String nome,List<Aluno>alunos, List<Curso> curso, List<Professor> professor) {
		super();
		this.nome = "";
		curso = new ArrayList<Curso>();
		professores = new ArrayList<Professor>();
		alunos = new ArrayList<Aluno>();
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public List<Curso> getcurso() {
		return curso;
	}

	public void setcurso(List<Curso> curso) {
		this.curso = curso;
	}

	public List<Professor> getprofessor() {
		return professores;
	}

	public void setprofessor(List<Professor> professor) {
		this.professores = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
