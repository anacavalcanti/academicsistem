package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Boletim;

@RestController
@RequestMapping("/boletim")
public class BoletimResources<BoletimRepositorio> {
	
	@Autowired
	BoletimRepositorio boletimRepositorio;
	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		new Boletim(0, 0);
		return null;
		
		
	}
}
