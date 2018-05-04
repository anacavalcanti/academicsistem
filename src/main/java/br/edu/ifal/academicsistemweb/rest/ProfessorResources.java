package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;

@RestController
@RequestMapping("/Professor")
public class ProfessorResources {
	
	@Autowired
	ProfessorRepositorio professorRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Professor e = new Professor(1, "Fulano", "Superior", "11111111", null);
		
		professorRepositorio.save(e);
		return "ok";
	}
}
