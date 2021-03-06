package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.ifal.academicsistemweb.modelo.Assunto;

public interface AssuntoRepositorio extends JpaRepository<Assunto, Integer>{
	List<Assunto> findByNomeLike(String nome);
}
