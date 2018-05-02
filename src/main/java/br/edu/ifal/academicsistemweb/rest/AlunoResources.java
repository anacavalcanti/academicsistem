package br.edu.ifal.academicsistemweb.rest;

	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

	import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.TipoAluno;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;


	@RestController
	@RequestMapping("/aluno")
	public class AlunoResources {
		
		@Autowired
		AlunoRepositorio alunoRepositorio;

		@RequestMapping (value="carregar", method=RequestMethod.GET)
		public String init() {
			Aluno e = new Aluno(Integer.valueOf(1), 1234, "Maria", "RUa Marechal", TipoAluno.Sembolsa, null);
			
			alunoRepositorio.save(e);
			return "ok";
		}
	}
