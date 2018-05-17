package br.edu.ifal.academicsistemweb.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "nota")
public class Notas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Disciplina disciplina;

	@Column
	private double valor;

	@ElementCollection
	private List<String> Notas = new ArrayList<>();

	public Notas(long i, Aluno aluno2, Disciplina disciplina, double valor) {
		super();
		this.id = i;
		this.aluno = (Aluno) aluno2;
		this.disciplina = disciplina;
		this.valor = valor;
	}

	public Notas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(int i) {
		this.id = (long) i;
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
		Notas other = (Notas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

