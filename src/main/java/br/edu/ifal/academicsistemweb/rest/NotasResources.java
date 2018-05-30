package br.edu.ifal.academicsistemweb.rest;


	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;

@RestController
@RequestMapping("/api/notas")
public class NotasResources {
		
		@Autowired
		NotaRepositorio notasRepositorio;
		
		@Autowired
		AlunoRepositorio alunoRepositorio;
		
		@Autowired
		DisciplinaRepositorio disciplinaRepositorio;
		


		@RequestMapping (value="carregar", method=RequestMethod.GET)
		public String carregar() {
			
			List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
			
			List<Aluno> alunos = alunoRepositorio.findAll();
			
			for (Disciplina disciplina : disciplinas) {
				
				if(disciplina.getNome().equals("Tópicos especiais em informática")) {
					
					for(Aluno aluno : alunos) {
						if(aluno.getMatricula().equals("222")) {
							Notas nota = new Notas();
			
							nota.setAluno(aluno);
							nota.setDisciplina(disciplina);
							nota.setValor(10.0);
			
							notasRepositorio.save(nota);
		
						}
					}
				}
			}	
			
			return "ok";
}
		@RequestMapping(value = "{id}/detalhes", method = RequestMethod.GET)
		
		public Notas buscar(@PathVariable("id") Integer id) {
			
			return notasRepositorio.getOne(id);
		}
		
		@RequestMapping(value = "listar/todas", method=RequestMethod.GET)
		
		public List<Notas> listar(){
			
			return notasRepositorio.findAll();
		}
		
		@RequestMapping(value = "salvar", method=RequestMethod.POST)
		public Notas salvar(@RequestBody Notas nota) {
				
			notasRepositorio.save(nota);
			
			return nota;
		}
	}



