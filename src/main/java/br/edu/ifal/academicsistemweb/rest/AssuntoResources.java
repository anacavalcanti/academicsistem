package br.edu.ifal.academicsistemweb.rest;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

	import br.edu.ifal.academicsistemweb.modelo.Assunto;
	import br.edu.ifal.academicsistemweb.repositorio.AssuntoRepositorio;


@RestController
@RequestMapping("/assunto")
public class AssuntoResources {
	
	@Autowired
	AssuntoRepositorio assuntoRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String init() {
		Assunto assunto = new Assunto("poo");
		
		assuntoRepositorio.save(assunto);
		return "ok";
		
	}
}
