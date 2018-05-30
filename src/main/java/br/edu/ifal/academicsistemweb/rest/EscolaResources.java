package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Escola;
import br.edu.ifal.academicsistemweb.repositorio.EscolaRepositorio;

@RestController
@RequestMapping("/api/escola")
public class EscolaResources {
	
	@Autowired
	EscolaRepositorio escolaRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String carregar() {
		Escola escola = new Escola();
		
		escola.setnome("IFAL- Rio Largo");
		escolaRepositorio.save(escola);
		return "ok";
	}
	
	@RequestMapping(value = "{id}/detalhes", method = RequestMethod.GET)
	
	public Escola buscar(@PathVariable("id") Integer id) {
		
		return escolaRepositorio.getOne(id);
	}
	
	@RequestMapping(value = "listar/todas", method=RequestMethod.GET)
	
	public List<Escola> listar(){
		
		return escolaRepositorio.findAll();
	}
	
	@RequestMapping(value = "pesquisar", method = RequestMethod.GET)
	
	public List<Escola> pesquisar(
			
		@RequestParam(name = "nome", defaultValue = "ALL")String nome){
		
		return escolaRepositorio.findByNome(nome);
	}
	
	@RequestMapping(value = "salvar", method=RequestMethod.POST)
	public Escola salvar(@RequestBody Escola escola) {
			
		escolaRepositorio.save(escola);
		
		return escola;
	}
	
}
