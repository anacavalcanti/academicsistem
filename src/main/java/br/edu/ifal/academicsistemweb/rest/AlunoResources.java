package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;

@RestController
@RequestMapping("/api/aluno")
public class AlunoResources {

	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@GetMapping("/iniciar")
	public String iniciar() {

		return "";

	}

	@GetMapping("/{id}")
	public Aluno buscar(@PathVariable Integer id) {
		Aluno a = alunoRepositorio.findById(id).get();

		return a;
	}

	@GetMapping("/listar")
	public List<Aluno> listar() {
		return alunoRepositorio.findAll();
	}

	@GetMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		alunoRepositorio.deleteById(id);
	}

}