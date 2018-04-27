package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Escola;
import br.edu.ifal.academicsistemweb.repositorio.EscolaRepositorio;

@RestController
@RequestMapping("/escola")
public class EscolaResources {
	
	@Autowired
	EscolaRepositorio escolaRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Escola e = new Escola("IFAL-RL");
		
		escolaRepositorio.save(e);
		return "ok";
	}
}
