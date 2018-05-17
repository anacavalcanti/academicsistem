package br.edu.ifal.academicsistemweb;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Endereco;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GeradorDeNotasTeste {
	
	private Aluno kely;
	private Disciplina disciplina;
	private Professor professor;
	private Notas nota;
	private GeradorDeNotasTeste gerenciador;
	private boolean resultado, resultadoEsperado;
	
	@Before
	public void CriacaoDasClassesParaOsTestes() {
		this.disciplina = new Disciplina();
		this.professor = new Professor();
		this.kely = new Aluno();
		this.nota = new Notas();
		this.gerenciador = new GeradorDeNotasTeste();
		
		
		disciplina.setId(1);;
		disciplina.setNome("PQS");
		professor.setId(1);
		professor.setNome("Priscylla");
		professor.setCpf("111.111.111-11");
		professor.setFormacao("Ciências da Computação");
		professor.setTipoProfessor(TipoProfessor.Efetivo);
		
		
		kely.setId(1);
		kely.setMatricula("2018-2");
		kely.setNome("Kely");
		kely.setEndereco(new Endereco("Rua x","Bairro Y", "Cidade z", 1));
		kely.getTelefones().add("(82)98706-7383");
		
		
		nota.setId(1);
		nota.setAluno(kely);
		nota.setDisciplina(disciplina);
		nota.setValor(10);
		
	}
	
	@Test
	public void deveFuncionarParaNotasMenoresQueZero() {
		
		nota.setValor(-2);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = false;
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	private boolean setarNotas(Notas nota2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public void deveFuncionarParaNotasMaioresQueDez() {
		
		nota.setValor(11);

		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = false;
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void deveFuncionarParaNotasSemAluno() {
		
		nota.setAluno(null);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = false;
		
		assertEquals(resultadoEsperado, resultado);	
	}
	
	@Test
	public void deveFuncionarParaNotasSemDisciplina() {
		
		nota.setDisciplina(null);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = false;
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void deveFuncionarParaNotasSemDisciplinaEaluno() {
		
		nota.setAluno(null);
		nota.setDisciplina(null);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = false;
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void deveFuncionarParaNotaDez() {
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = true;
		
		assertEquals(resultadoEsperado, resultado);
		
	}
	
	@Test
	public void deveFuncionarParaNotaEntreZeroEDez() {
		
		nota.setValor(7.5);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = true;
		
		assertEquals(resultadoEsperado, resultado);
		
	}
	
	@Test
	public void deveFuncionarParaNotaZero() {
		
		nota.setValor(0.0);
		
		resultado = gerenciador.setarNotas(nota);
		
		resultadoEsperado = true;
		
		assertEquals(resultadoEsperado, resultado);
		
	}
}
