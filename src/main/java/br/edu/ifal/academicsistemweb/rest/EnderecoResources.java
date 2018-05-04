package br.edu.ifal.academicsistemweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/endereço")
public class EndereçoResources {
	
	@Autowired
	EndereçoRepositorio endereçoRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Endereço e = new EndereçoResources()
		
		EndereçoRepositorio.save(e);
		return "ok";
	}
}