package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "escola")
public class Escola {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String nome;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "nome_escola")
	private Set<Aluno> alunos = new HashSet<>();


	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="escola_curso",
	joinColumns = {
			@JoinColumn(name = "id_escola", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "id_curso", referencedColumnName = "id")})
	private Set<Curso> cursos = new HashSet<>();;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name ="nome_escola")
	private Set<Professor> professores = new HashSet<>();;
	
	public Escola() {
		super();
	}

	public Escola(String nome,Set<Aluno>alunos, Set<Curso> curso, Set<Professor> professor) {
		super();
		this.nome = nome;
		this.cursos = curso;
		this.professores = professor;
		this.alunos = alunos;
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

	public Set<Curso> getcurso() {
		return cursos;
	}

	public void setcurso(Set<Curso> curso) {
		this.cursos = curso;
	}

	public Set<Professor> getprofessor() {
		return professores;
	}

	public void setprofessor(Set<Professor> professor) {
		this.professores = professor;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public boolean adicionarAluno(Aluno aluno) {
		if(alunos.contains(aluno)) {
			return false;
		}else {
			alunos.add(aluno);
			return true;
		}
	}
	
	public boolean removerAluno(Aluno aluno) {
		if(alunos.contains(aluno)) {
			alunos.remove(aluno);
			return true;
		}else {
			return false;
		}		
	}

	public boolean adicionarProfessor(Professor professor) {
		if(professores.contains(professor)) {
			return false;
		}else {
			professores.add(professor);
			return true;
		}
	}
	
	public boolean removerProfessor(Professor professor) {
		if(professores.contains(professor)) {
			professores.remove(professor);
			return true;
		}else {
			return false;
		}		
	}

	public boolean adicionarCurso(Curso curso) {
		if(cursos.contains(curso)) {
			return false;
		}else {
			cursos.add(curso);
			return true;
		}
	}
	
	public boolean removerCurso(Curso curso) {
		if(cursos.contains(curso)) {
			cursos.remove(curso);
			return true;
		}else {
			return false;
		}
	}
}
