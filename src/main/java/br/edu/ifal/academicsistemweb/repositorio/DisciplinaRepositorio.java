package br.edu.ifal.academicsistemweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifal.academicsistemweb.modelo.Disciplina;

public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Integer>{
	List<Disciplina> findByPeriodo(Integer periodo);
	
	@Query("SELECT Nome FROM Disciplina WHERE periodo = :pPeriodo")
	public List<String> findNomePeriodo(@Param("pPeriodo") Integer periodo);
}
