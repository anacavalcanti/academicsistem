package br.edu.ifal.academicsistemweb.modelo;

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
@Table(name="curso")
public class Curso{
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@Column
		private String nome;
		
		@JsonIgnore
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinTable(name="curso_disciplina",
			joinColumns = {
				@JoinColumn(name = "id_curso", referencedColumnName = "id")},
			inverseJoinColumns = {
				@JoinColumn(name = "id_disciplina", referencedColumnName = "id")})
		private Set<Disciplina> disciplinas = new HashSet<>();
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "id_curso")
		private Set<Aluno> alunos = new HashSet<Aluno>();
		
	
		
		public Curso() {
			super();
		}
		
		public Curso(Integer id, String nome, Set<Disciplina> disciplinas, Set<Aluno> alunos) {
			super();
			this.id = id;
			this.nome = nome;
			this.disciplinas = disciplinas;
			this.alunos = alunos;
					}

		public 	Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		public String getnome() {
			return nome;
		}
		
		public void setnome(String nome) {
			this.nome=nome;
		}
		
		public Set<Aluno> getaluno(){
			return alunos;
			
		}
		public void setaluno(Set<Aluno> alunos) {
			this.alunos = alunos;
		}
		public Set<Disciplina> getdisciplina(){
			return disciplinas;
		}
		public void setdisciplina (Set<Disciplina> disciplinas) {
			this.disciplinas = disciplinas;
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
		
		public boolean adicionarDisciplina(Disciplina disciplina) {
			if(disciplinas.contains(disciplina)) {
				return false;
			}else {
				disciplinas.add(disciplina);
				return true;
			}
		}
		
		public boolean removerDisciplina(Disciplina disciplina) {
			if(disciplinas.contains(disciplina)) {
				disciplinas.remove(disciplina);
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
			Curso other = (Curso) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Curso [id=" + id + ", nome=" + nome + ", disciplinas=" + disciplinas + ", alunos=" + alunos + "]";
		}
	}

