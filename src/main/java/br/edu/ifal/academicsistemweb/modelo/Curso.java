package br.edu.ifal.academicsistemweb.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso{
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@Column
		private String nome;
		
		@OneToMany
		private List<Aluno> aluno;
		
		@OneToMany
		private List<Disciplina> disciplina;
		
		public Curso() {
			// TODO Auto-generated constructor stub
		}
		
		public Curso(Integer id, String nome) {
			super();
			this.id = id;
			this.nome = nome;
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
		
		public List<Aluno> getaluno(){
			return aluno;
			
		}
		public void setaluno(List<Aluno> aluno) {
			this.aluno = aluno;
		}
		public List<Disciplina> getdisciplina(){
			return disciplina;
		}
		public void setdisciplina (List<Disciplina> disciplina) {
			this.disciplina = disciplina;
		}
		
			
}

