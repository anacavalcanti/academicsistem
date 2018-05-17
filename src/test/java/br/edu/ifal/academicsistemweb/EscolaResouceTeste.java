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

import br.edu.ifal.academicsistemweb.modelo.Escola;
import br.edu.ifal.academicsistemweb.repositorio.EscolaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EscolaResouceTeste {
	
	final String BASE_PATH = "http://localhost:8080/api/escola";
	
	@Autowired
	private EscolaRepositorio escolaRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	
	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		escolaRepository.deleteAll();
		//inserir algumas escolas
		Escola escola = new Escola();
		escola.setnome("IFAL Rio Largo");
		escolaRepository.save(escola);
		
		Escola escola1 = new Escola();
		escola1.setnome("IFAL Maceió");
		escolaRepository.save(escola1);
		
		Escola escola2 = new Escola();
		escola2.setnome("IFAL Muricí");
		escolaRepository.save(escola2);	
	}

	@Test
	public void deveFuncionarAListagemDeTodasAsEscolas() throws JsonParseException, JsonMappingException, IOException {
		String response = restTemplate.getForObject(BASE_PATH + "/listar/todas",String.class);
		
		List<Escola>  escolas = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Escola.class));
		
		int tamanhoEsperadoDaLista = 3;
		
		assertEquals(tamanhoEsperadoDaLista,escolas.size());
	}
}
