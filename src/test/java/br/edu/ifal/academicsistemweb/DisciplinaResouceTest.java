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

import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DisciplinaResouceTest {
	
	final String BASE_PATH = "http://localhost:8080/api/disciplina";
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		disciplinaRepository.deleteAll();
		
		
		Disciplina disciplina = new Disciplina();
		
		disciplina.setNome("Projeto de Interface do usuário");
		
		disciplinaRepository.save(disciplina);
		
		
		
		Disciplina disciplina1 = new Disciplina();
		
		disciplina1.setNome("Gestão de qualidade de software");
		
		disciplinaRepository.save(disciplina1);
		
		
		
		Disciplina disciplina2 = new Disciplina();
		
		disciplina2.setNome("Tópicos especiais em informática");
		
		disciplinaRepository.save(disciplina2);
	}

	@Test
	public void deveFuncionarAListagemDeTodasAsDisciplinas() throws JsonParseException, JsonMappingException, IOException {
		String response = restTemplate.getForObject(BASE_PATH + "/listar/todas",String.class);
		
		List<Disciplina>  disciplinas = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Disciplina.class));
		
		int tamanhoEsperadoDaLista = 3;
		
		assertEquals(tamanhoEsperadoDaLista,disciplinas.size());
	}

}

