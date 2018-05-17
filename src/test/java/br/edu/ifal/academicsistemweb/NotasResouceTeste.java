package br.edu.ifal.academicsistemweb;


import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NotasResouceTeste {

	final String BASE_PATH = "http://localhost:8080/api/nota";
	
	@Autowired
	private NotaRepositorio notaRepository;
	@Autowired
	private DisciplinaRepositorio disciplinaRepository;
	@Autowired
	private AlunoRepositorio alunoRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	
	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		notaRepository.deleteAll();
		
		Disciplina disciplina = disciplinaRepository.getOne(1);
		Aluno aluno = alunoRepository.getOne(1);
		notaRepository.save(new Notas(1, aluno, disciplina, 10.0));
		
		Disciplina disciplina1 = disciplinaRepository.getOne(2);
		Aluno aluno1 = alunoRepository.getOne(16);
		notaRepository.save(new Notas(2, aluno1, disciplina1, 9.0));
		
		Disciplina disciplina2 = disciplinaRepository.getOne(3);
		Aluno aluno2 = alunoRepository.getOne(3);
		notaRepository.save(new Notas(3, aluno2, disciplina2, 8.0));
	}

	@Test
	public void deveFuncionarAListagemDeTodasAsNotas() throws JsonParseException, JsonMappingException, IOException {
		String response = restTemplate.getForObject(BASE_PATH + "/listar/todas",String.class);
		
		List<Notas>  notas = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Notas.class));
		
		int tamanhoEsperadoDaLista = 3;
		
		assertEquals(tamanhoEsperadoDaLista,notas.size());
	}

}

