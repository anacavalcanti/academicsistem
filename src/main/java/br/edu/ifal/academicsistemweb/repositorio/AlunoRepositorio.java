package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Aluno;

public interface AlunoRepositorio 
	extends JpaRepository<Aluno, Integer>{
		
	}


