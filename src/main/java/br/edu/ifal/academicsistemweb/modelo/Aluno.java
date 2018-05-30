package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;






@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private int idade;

	@Column
	private char sexo;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "alunos")
	private Set<Disciplina> disciplinas = new HashSet<Disciplina>();

	@Column
	private String matricula;

	@Embedded
	private Endereco endereco = new Endereco();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "telefone_aluno",
	joinColumns = @JoinColumn(name = "id_aluno"))
	@Column(length = 20 , name = "numero_aluno", nullable = false)
	private List<String> telefones = new ArrayList<>();
	

	@Column(name = "aluno_Enum", nullable = true)
	@Enumerated(EnumType.STRING)
	private TipoAluno tipoDeAluno = TipoAluno.BolsistaAssistencia;

	public Aluno(String matricula) {
		super();
		this.matricula = matricula;
	}

	public Aluno() {
		super();
	}

	
	
	public Aluno(Long id, String nome, int idade, char sexo, Set<Disciplina> disciplinas, String matricula,
			Endereco endereco, List<String> telefones) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.disciplinas = disciplinas;
		this.matricula = matricula;
		this.endereco = endereco;
		this.telefones = telefones;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}	

	public Set<Disciplina> getDisciplinas() {
		return  disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;

	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public boolean adicionarTelefone(String telefone) {
		if(telefones.contains(telefone)) {
			return false;
		}else {
			telefones.add(telefone);
			return true;
		}
	}
	
	public boolean removerTelefone(String telefone) {
		if(telefones.contains(telefone)) {
			telefones.remove(telefone);
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", disciplinas=" + disciplinas
				+ ", endereco=" + endereco + ", telefones=" + telefones + "]";
	}

}