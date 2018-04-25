package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno extends Pessoa{

	@Column
	private Integer matricula;
	
	
	@ElementCollection
	private List<String> telefones = new ArrayList<>();
	
	@OneToMany
	private List<Notas> Notas;
	
	
	@Column(name = "tipo_aluno", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAluno tipoAluno;

	public Aluno(Integer id, int matricula, String nome,String endereco, TipoAluno tipoAluno, List<Notas>notas) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.Notas = notas;
		this.matricula = matricula;
		this.tipoAluno = tipoAluno;
	}

	public Aluno() {
		super();
	}
	
	
	public TipoAluno getTipoAluno() {
		return tipoAluno;
	}

	public void setTipoAluno(TipoAluno tipoAluno) {
		this.tipoAluno = tipoAluno;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
		
	public List<Notas> getNotas() {
		return Notas;
	}

	public void setNotas(List<Notas> notas) {
		Notas = notas;
	}

	@ElementCollection
	@CollectionTable(name ="aluno_telefones",
		joinColumns = @JoinColumn(name = "cod_aluno"))
	@AttributeOverrides({@AttributeOverride(name = "endereco",
	column=@Column(name = "endereco", length = 50, nullable = false)) })
	public List<String> getTelefones(){
		return telefones;
	}
	public void setTelefones(List<String>telefones) {
		this.telefones = telefones;
	}

	
	
}