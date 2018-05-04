package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Boletim;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;

public interface BoletimRepositorio extends JpaRepository<Boletim, Integer> {
	List<Boletim> findByNomeContaining(String nome);

	List<Notas> getNotas(Aluno aluno, Disciplina disciplina);
	
}
