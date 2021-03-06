package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;

public interface NotaRepositorio extends JpaRepository<Notas, Integer>{
	public List<Notas> findByAlunoAndDisciplina(Aluno a , Disciplina d);
	public List<Notas> findByAluno(Aluno a);
}
