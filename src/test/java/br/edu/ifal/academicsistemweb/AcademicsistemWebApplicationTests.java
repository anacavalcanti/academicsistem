package br.edu.ifal.academicsistemweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifal.academicsistemweb.modelo.Curso;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademicsistemWebApplicationTests {

	@Autowired
	private CursoRepositorio cursoRepository;
		
	@Test
	public void contextLoads() {
	
		Curso c = new Curso(1, "Informatica", null, null);
		
		cursoRepository.save(c);
		
	}

		

}
