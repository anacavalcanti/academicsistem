package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifal.academicsistemweb.modelo.Aluno;

public interface AlunoRepositorio extends JpaRepository<Aluno, Integer>	{
		
	List<Aluno> findByNomeLike(String nome);
	}


