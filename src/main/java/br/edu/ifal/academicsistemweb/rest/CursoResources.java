package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Curso;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;

@RestController
@RequestMapping("/Curso")
public class CursoResources {
	
	@Autowired
	CursoRepositorio CursoRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Curso e = new Curso("IFAL-RL");
		
		CursoRepositorio.save(e);
		return "ok";
	}
}