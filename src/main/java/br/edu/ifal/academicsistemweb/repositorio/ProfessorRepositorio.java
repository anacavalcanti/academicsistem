package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Professor;

public interface ProfessorRepositorio extends JpaRepository<Professor, Integer>{
	
}


