package br.edu.ifal.academicsistemweb.rest;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

	import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;
	

	@RestController
	@RequestMapping("/notas")
	public class NotasResources {
		
		@Autowired
		NotaRepositorio notasRepositorio;

		@RequestMapping (value="carregar", method=RequestMethod.GET)
		public String init() {
			Notas e = new Notas();
			
			notasRepositorio.save(e);
			return "ok";
		}
	}



