package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pessoa")
public class PessoaResources<PessoaRepositorio> {
	
	@Autowired
	PessoaRepositorio pessoaRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		return null;
		
	
	}
}


