package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "nota")
public class Notas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Disciplina disciplina;

	@Column
	private double valor;

	@ElementCollection
	private List<String> Notas = new ArrayList<>();

	public Notas(Integer id, Aluno aluno, Disciplina disciplina, double valor) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.valor = valor;
	}

	public Notas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@ElementCollection
	@CollectionTable(name = "notas", joinColumns = @JoinColumn(name = "cod_nota"))
	@Column(name = "notas", length = 20, nullable = false)

	public List<String> getNotas() {
		return Notas;
	}

	public void setNotas(List<String> notas) {
		this.Notas = notas;
	}

}
