package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Integer>{
	
	List<Curso> findByNomeContaining(String nome);
}


