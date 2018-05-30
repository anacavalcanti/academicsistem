package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;


@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaResources {
	
	@Autowired
	DisciplinaRepositorio disciplinaRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String carregar() {
		
		Disciplina disciplina = new Disciplina();
		
		disciplina.setNome("Interface");
		
		disciplinaRepositorio.save(disciplina);
		
		return "ok";
	}
	

	@RequestMapping(value = "{id}/detalhes", method = RequestMethod.GET)
	public Disciplina buscar(@PathVariable("id") Integer id) {
		return disciplinaRepositorio.getOne(id);
	}
	
	@RequestMapping(value = "listar/todas", method=RequestMethod.GET)
	
	public List<Disciplina> listar(){
		
		return disciplinaRepositorio.findAll();
	}
	
	@RequestMapping(value = "pesquisar", method = RequestMethod.GET)
	
	public List<Disciplina> pesquisar(
			
		@RequestParam(name = "nome", defaultValue = "ALL")String nome){
		
		return disciplinaRepositorio.findByNome(nome);
	}
	
	@RequestMapping(value = "salvar", method=RequestMethod.POST)
	public Disciplina salvar(@RequestBody Disciplina disciplina) {
			
		disciplinaRepositorio.save(disciplina);
		
		return disciplina;
	}
	
	
}