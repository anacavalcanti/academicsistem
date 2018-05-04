package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Curso;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;

@RestController
@RequestMapping("/api/Curso")
public class CursoResources {

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@GetMapping("/{id}")
	public Curso buscar(@PathVariable Integer id) {
		return cursoRepositorio.findById(id).get();
	}

	@GetMapping("/listar")
	public List<Curso> listar(){
		return cursoRepositorio.findAll();
	}
	
	@GetMapping("/pesquisar")
	public List<Curso> pesquisar(@RequestParam("nome")String nome){
		List<Curso> cursos = cursoRepositorio.findByNomeContaining(nome);
			return cursos;
	}
	
	@GetMapping ("/deletar/{id}")
	public void deletar (@PathVariable Integer id) {
		cursoRepositorio.deleteById(id);
	}
	

}