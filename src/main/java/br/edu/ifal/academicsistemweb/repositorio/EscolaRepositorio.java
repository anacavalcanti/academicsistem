package br.edu.ifal.academicsistemweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifal.academicsistemweb.modelo.Escola;

public interface EscolaRepositorio 

 extends JpaRepository <Escola, Integer>{
	
}
