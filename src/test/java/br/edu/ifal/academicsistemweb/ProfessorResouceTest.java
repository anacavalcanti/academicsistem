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

import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProfessorResouceTest {
final String BASE_PATH = "http://localhost:8080/api/professor";
	
	@Autowired
	private ProfessorRepositorio professorRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		professorRepository.deleteAll();
		
		
		Professor professor = new Professor();
		professor.setNome("Luíz");
		professor.setCpf("111.111.111-11");
		professor.setFormacao("Analista de Sistemas");
		professor.setTipoProfessor(TipoProfessor.Subistituto);
		
		professorRepository.save(professor);
		
		Professor professor1 = new Professor();
		professor1.setNome("Priscylla");
		professor1.setCpf("222.222.222-22");
		professor1.setFormacao("Ciências da Computação");
		professor1.setTipoProfessor(TipoProfessor.Efetivo);
		
		professorRepository.save(professor1);
		
		Professor professor2 = new Professor();
		professor2.setNome("Leonardo");
		professor2.setCpf("333.333.333-33");
		professor2.setFormacao("Engenharia Elétrica");
		professor2.setTipoProfessor(TipoProfessor.Subistituto);
		
		professorRepository.save(professor2);
	}

	@Test
	public void deveFuncionarAListagemDeTodosOsProfessores() throws JsonParseException, JsonMappingException, IOException {
		String response = restTemplate.getForObject(BASE_PATH + "/listar/todos",String.class);
		
		List<Professor>  professores = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Professor.class));
		
		int tamanhoEsperadoDaLista = 3;
		
		assertEquals(tamanhoEsperadoDaLista, professores.size());
	}
}
