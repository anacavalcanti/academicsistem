package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Disciplina;

public interface DisciplinaRepositorio 

	extends JpaRepository<Disciplina, Integer>{
	
}
