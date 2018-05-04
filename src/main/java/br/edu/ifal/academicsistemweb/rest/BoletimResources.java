package br.edu.ifal.academicsistemweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Boletim;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.BoletimRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;

@RestController
@RequestMapping("/api/Boletim")
public class BoletimResources {

	@Autowired
	BoletimRepositorio boletimRepositorio;
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@Autowired
	DisciplinaRepositorio disciplinaRepositorio;
	
	@RequestMapping(value="{alunoId}/notas")
	public List<Notas> notasAluno(
			@PathVariable("alunoId") Integer alunoId, 
			@RequestParam(value="disciplinaId",defaultValue="0") Integer disciplinaId){
		
		Aluno aluno = alunoRepositorio.getOne(alunoId);
		
		if(disciplinaId==0)
			return boletimRepositorio.getNotas(aluno, null);
		else {
			Disciplina disciplina = disciplinaRepositorio.getOne(disciplinaId);
			
			return boletimRepositorio.getNotas(aluno, disciplina);
		}
			
	}
	
}