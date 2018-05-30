package br.edu.ifal.academicsistemweb;



import static org.junit.Assert.*;

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
import br.edu.ifal.academicsistemweb.modelo.Endereco;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AlunoResouceTest {
	
	final String BASE_PATH = "http://localhost:8080/api/aluno";
	
	@Autowired
	private AlunoRepositorio alunoRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		alunoRepository.deleteAll();
		
		Aluno pedro = new Aluno();
		pedro.setNome("Pedro");
		pedro.setMatricula("222");
		pedro.setEndereco(new Endereco("Rua das Arvores","Centro", "Maceió", 11));
		pedro.getTelefones().add("(82)9.9925-9987");
		pedro.getTelefones().add("(82)9.8823-2514");
		
		alunoRepository.save(pedro);
		
		Aluno ana = new Aluno();
		ana.setNome("Ana");
		ana.setMatricula("123");
		ana.setEndereco(new Endereco("Rua da Lua", "Mata do Rolo", "Rio Largo", 02));
		ana.getTelefones().add("(82)9.9821-6987");
		ana.getTelefones().add("(82)9.9623-2214");
		
		alunoRepository.save(ana);
		
		Aluno kely = new Aluno();
		kely.setNome("Kely");
		kely.setMatricula("425");
		kely.setEndereco(new Endereco("Rua getulio vargas", "Mata do Rolo", "Rio Largo", 24));
		kely.getTelefones().add("(82)9.9785-6987");
		kely.getTelefones().add("(82)9.9254-2814");
		
		alunoRepository.save(kely);
	}

	@Test
	public void deveFuncionarAListagemDeTodosOsAlunos() throws JsonParseException, JsonMappingException, IOException {
		String response = restTemplate.getForObject(BASE_PATH + "/listar",String.class);
		
		List<Aluno>  alunos = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Aluno.class));
		
		int tamanhoEsperadoDaLista = 3;
		
		assertEquals(tamanhoEsperadoDaLista,alunos.size());
	}
	
	@Test
	public void deveFuncionarACriacaoDeUmNovoAluno() throws JsonParseException, JsonMappingException, IOException {
		//Criar um novo aluno
		Aluno aluno = new Aluno("1234");
		aluno.setNome("Keila");
		aluno.setMatricula("125");
		aluno.setEndereco(new Endereco("Rua getulio vargas", "Mata do Rolo", "Rio Largo", 24));
		aluno.getTelefones().add("(82)9.9785-6987");
		aluno.getTelefones().add("(82)9.9254-2814");
		
		//Fazendo um HTTP request do tipo POST passando aluno como parâmetro
		restTemplate.postForObject(BASE_PATH+"/salvar",
				aluno, Aluno.class);
		
		String response = restTemplate.getForObject(BASE_PATH + "/listar",String.class);
		
		List<Aluno>  alunos = MAPPER.readValue(response,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Aluno.class));
	
		//assertEquals(4, alunos.size());
		assertEquals("Keila", alunos.get(3).getNome());
	
	}
}
