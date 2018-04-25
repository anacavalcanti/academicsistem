package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Notas;

public interface NotaRepositorio 

extends JpaRepository<Notas, Integer>{
	
}
