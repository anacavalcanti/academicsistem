package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Endereco;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;


@RestController
@RequestMapping("/api/aluno")
public class AlunoResources {

	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@RequestMapping(value = "carregar", method=RequestMethod.GET)
	public String carregar() {
		
		Aluno aluno = new Aluno();
		
		aluno.setNome("Maria José");
		aluno.setMatricula("1345");
		aluno.setEndereco(new Endereco("X", "Y", "Z", 12));
				
			
		alunoRepositorio.save(aluno);
		
		return "ok";
	}

	@RequestMapping(value ="/{id}/detalhes", method = RequestMethod.GET)
	
	public Aluno buscar(@PathVariable Integer id) {
		return alunoRepositorio.getOne(id);

		
	}

	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public List<Aluno> listar() {
		return alunoRepositorio.findAll();
	}
	
@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	
	public List<Aluno> pesquisar(
			
		@RequestParam(name = "nome", defaultValue = "ALL")String nome){
		
		return alunoRepositorio.findByNomeLike(nome);
	}
	
	@RequestMapping(value = "/salvar", method=RequestMethod.POST)
	public Aluno salvar(@RequestBody Aluno aluno) {
		alunoRepositorio.save(aluno);
		return aluno;
	}
	
	
	


}