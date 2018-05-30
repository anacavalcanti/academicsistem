package br.edu.ifal.academicsistemweb.modelo;

import java.util.HashSet;
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
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nome;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "disciplina_professor",
	joinColumns = {
			@JoinColumn(name = "id_disciplina", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "id_professor", referencedColumnName = "id")})
	private Set<Professor> professores = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="disciplina_aluno",
	joinColumns = {
			@JoinColumn(name = "id_disciplina", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "id_aluno", referencedColumnName = "id")})
	private Set<Aluno> alunos = new HashSet<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="disciplina_assunto",
	joinColumns = {
			@JoinColumn(name = "id_disciplina", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "id_assunto", referencedColumnName = "id")})
	private Set<Assunto> assuntos = new HashSet<>();
	

	public Disciplina() {
		super();
	}

	public Disciplina(Long id, String nome, Set<Professor> professores, Set<Aluno> alunos, Set<Assunto> assuntos) {
		super();
		this.id = id;
		this.nome = nome;
		this.professores = professores;
		this.alunos = alunos;
		this.assuntos = assuntos;
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

	public Set<Professor> getProfessor() {
		return professores;
	}
	public void setProfessor(Set<Professor> professores) {
		this.professores = professores;
	}


	public Set<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(Set<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Assunto> getassunto() {
		return assuntos;
	}

	public void setAssunto(Set<Assunto> assunto) {
		this.assuntos = assunto;
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
	
	public boolean adicionarAssunto(Assunto assunto) {
		if(assuntos.contains(assunto)) {
			return false;
		}else {
			assuntos.add(assunto);
			return true;
		}
	}
	
	public boolean removerAssunto(Assunto assunto) {
		if(assuntos.contains(assunto)) {
			assuntos.remove(assunto);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", professores=" + professores + ", alunos=" + alunos
				+ ", assuntos=" + assuntos + "]";
	}


}
