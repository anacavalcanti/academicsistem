package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Curso;

public interface CursoRepositorio
	extends JpaRepository<Curso, Integer>{
	
}


