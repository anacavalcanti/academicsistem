package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Professor;

public interface ProfessorRepositorio extends JpaRepository<Professor, Integer>{

	public List<Professor> findByNome(String nome);
}


