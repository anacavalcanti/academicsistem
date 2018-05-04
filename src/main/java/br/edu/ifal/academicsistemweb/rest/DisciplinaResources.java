package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;


@RestController
@RequestMapping("/disciplina")
public class DisciplinaResources {
	
	@Autowired
	DisciplinaRepositorio disciplinaRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Disciplina D = new Disciplina();
		
		disciplinaRepositorio.save(D);
		return "ok";
	}
}