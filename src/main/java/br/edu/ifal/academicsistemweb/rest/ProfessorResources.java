package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;

@RestController
@RequestMapping("/api/Professor")
public class ProfessorResources {
	
	@Autowired
	ProfessorRepositorio professorRepositorio;

	@RequestMapping (value="carregar", method=RequestMethod.GET)
	public String carregar() {
		
		Professor professor = new Professor();
		
		professor.setNome("Priscylla");
		
		professorRepositorio.save(professor);
		return "ok";
	}
@RequestMapping(value = "{id}/detalhes", method = RequestMethod.GET)
	
	public Professor buscar(@PathVariable("id") Integer id) {
		
		return professorRepositorio.getOne(id);
	}
	
	@RequestMapping(value = "listar/todos", method=RequestMethod.GET)
	
	public List<Professor> listar(){
		
		return professorRepositorio.findAll();
	}
	
	@RequestMapping(value = "pesquisar", method = RequestMethod.GET)
	
	public List<Professor> pesquisar(
			
		@RequestParam(name = "nome", defaultValue = "ALL")String nome){
		
		return professorRepositorio.findByNome(nome);
	}
	
	@RequestMapping(value = "salvar", method=RequestMethod.POST)
	public Professor salvar(@RequestBody Professor professor) {
			
		professorRepositorio.save(professor);
		
		return professor;
	}
}
