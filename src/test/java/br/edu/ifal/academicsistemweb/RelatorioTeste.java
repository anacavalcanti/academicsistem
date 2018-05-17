package br.edu.ifal.academicsistemweb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.servico.Relatorio;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RelatorioTeste {

	private List<Aluno> alunos;
	private Disciplina disciplina;
	private Professor professor;
	private List<Notas> notas;
	private Relatorio relatorio;
	
	@Before
	public void criacaoDasEntidadesParaOsTestes() {
		this.alunos = new ArrayList<>();
		this.professor = new Professor();
		professor.setId(1);;
		professor.setNome("Maria");
		this.disciplina = new Disciplina();
		disciplina.setId(1);
		disciplina.setNome("Poo");
		this.notas = new ArrayList<>();
		this.relatorio = new Relatorio();
		
		Aluno ze = new Aluno();
		ze.setId(1);;
		ze.setNome("Zé");
		ze.setMatricula("123");
		
		Aluno ana = new Aluno();
		ana.setId(2);;
		ana.setNome("Ana");
		ana.setMatricula("254");
		
		Aluno joao = new Aluno();
		joao.setId(3);;
		joao.setNome("João");
		joao.setMatricula("222");
		
		Aluno piu = new Aluno();
		piu.setId(4);
		piu.setNome("Priscylla");
		piu.setMatricula("851");
		
		this.alunos.add(ze);
		this.alunos.add(ana);
		this.alunos.add(joao);
		this.alunos.add(piu);
	}
	
	@Test
	public void deveFuncionarParaAsNotasNaOrdemAleatoria() {
	

		this.notas.add(new Notas(1L, alunos.get(0), disciplina, 2.5));
		this.notas.add(new Notas(2L, alunos.get(1),disciplina, 9.5));
		this.notas.add(new Notas(3L, alunos.get(2),disciplina, 7.5));
		
		relatorio.gerar(notas);
		
		double maiorNotaEsperada = 9.5;
		double menorNotaEsperada = 2.5;
		
		
		assertEquals(maiorNotaEsperada, relatorio.getMaiorNota(), 0.001);
		assertEquals(menorNotaEsperada, relatorio.getMenorNota(), 0.001);
	}
	
	@Test
	public void deveFuncionarParaAsNotasNaOrdemCrescente() {
		
		this.notas.add(new Notas(1L, alunos.get(0), disciplina, 2.5));
		this.notas.add(new Notas(2L, alunos.get(1),disciplina, 9.5));
		this.notas.add(new Notas(3L, alunos.get(2),disciplina, 7.5));
		
		relatorio.gerar(notas);
		
		double maiorNotaEsperada = 9.5;
		double menorNotaEsperada = 2.5;
		
		assertEquals(maiorNotaEsperada, relatorio.getMaiorNota(), 0.001);
		assertEquals(menorNotaEsperada, relatorio.getMenorNota(), 0.001);
	}
	
	@Test
	public void deveFuncionarParaAsNotasNaOrdemDecrescente() {
		
		this.notas.add(new Notas(1L, alunos.get(0),disciplina, 9.5));
		this.notas.add(new Notas(2L, alunos.get(1),disciplina, 7.5));
		this.notas.add(new Notas(3L, alunos.get(2),disciplina, 2.5));
		
		relatorio.gerar(notas);
		
		double maiorNotaEsperada = 9.5;
		double menorNotaEsperada = 2.5;
		
		assertEquals(maiorNotaEsperada, relatorio.getMaiorNota(), 0.001);
		assertEquals(menorNotaEsperada, relatorio.getMenorNota(), 0.001);
	}
	
	@Test
	public void deveFuncionarComUmaUnicaNota() {
		
		notas.add(new Notas(1L, alunos.get(0), disciplina, 2.5));
		
		relatorio.gerar(notas);
		
		double maiorNotaEsperada = 2.5;
		double menorNotaEsperada = 2.5;
		
		
		assertEquals(maiorNotaEsperada, relatorio.getMaiorNota(), 0.001);
		assertEquals(menorNotaEsperada, relatorio.getMenorNota(), 0.001);
	}
	
	@Test
	public void deveFuncionarAIdentificacaoDas3MaioresNotasDosAlunos() {
	
		this.notas.add(new Notas(1L, alunos.get(0),disciplina, 9.0));
		this.notas.add(new Notas(2L, alunos.get(1), disciplina, 10.0));
		this.notas.add(new Notas(3L, alunos.get(2),disciplina, 8.0));
		this.notas.add(new Notas(4L, alunos.get(3),disciplina, 2.5));
		
		relatorio.gerar(notas);
		
		int quantidadeDeNotasEsperadas = 3;
		double primeiraNotaEsperada = 10.0;
		double segundaNotaEsperada = 9.0;
		double terceiraNotaEsperada = 8.0;
		
		assertEquals(quantidadeDeNotasEsperadas,relatorio.getTop3Notas().size());
		assertEquals(primeiraNotaEsperada,relatorio.getTop3Notas().get(0).getValor(), 0000.1);
		assertEquals(segundaNotaEsperada,relatorio.getTop3Notas().get(1).getValor(), 0000.1);
		assertEquals(terceiraNotaEsperada,relatorio.getTop3Notas().get(2).getValor(), 0000.1);
	}
	
	@Test
	public void deveFuncionarAIdentificacaoDas3MaioresNotas() {
	
		this.notas.add(new Notas(1L, alunos.get(0),disciplina, 9.0));
		this.notas.add(new Notas(2L, alunos.get(1), disciplina, 10.0));
		this.notas.add(new Notas(3L, alunos.get(2),disciplina, 8.0));
		
		relatorio.gerar(notas);
		
		int quantidadeDeNotasEsperadas = 3;
		double primeiraNotaEsperada = 10.0;
		double segundaNotaEsperada = 9.0;
		double terceiraNotaEsperada = 8.0;
		
		assertEquals(quantidadeDeNotasEsperadas,relatorio.getTop3Notas().size());
		assertEquals(primeiraNotaEsperada,relatorio.getTop3Notas().get(0).getValor(), 0000.1);
		assertEquals(segundaNotaEsperada,relatorio.getTop3Notas().get(1).getValor(), 0000.1);
		assertEquals(terceiraNotaEsperada,relatorio.getTop3Notas().get(2).getValor(), 0000.1);
	}
	
	@Test
	public void deveFuncionarParaIdentificarAs3MaioresNotasDeUmUnicoAluno() {
		
		notas.add(new Notas(1L, alunos.get(0), disciplina, 10.0));
		
		relatorio.gerar(notas);
		
		int quantidadeDeNotasEsperadas = 1;
		double primeiraNotaEsperada = 10.0;
		
		assertEquals(quantidadeDeNotasEsperadas,relatorio.getTop3Notas().size());
		assertEquals(primeiraNotaEsperada,relatorio.getTop3Notas().get(0).getValor(), 0000.1);
		
	}
	
	@Test
	public void deveFuncionarParaIdentificarAs3MaioresNotasPara2Alunos() {
			
		notas.add(new Notas(1L, alunos.get(0), disciplina, 10.0));
		notas.add(new Notas(2L, alunos.get(1), disciplina, 7.5));
		
		relatorio.gerar(notas);
		
		int quantidadeDeNotasEsperadas = 2;
		double primeiraNotaEsperada = 10.0;
		double segundaNotaEsperada = 7.5;
		
		assertEquals(quantidadeDeNotasEsperadas,relatorio.getTop3Notas().size());
		assertEquals(primeiraNotaEsperada,relatorio.getTop3Notas().get(0).getValor(), 0000.1);
		assertEquals(segundaNotaEsperada,relatorio.getTop3Notas().get(1).getValor(), 0000.1);
	}
	
	@Test
	public void deveFuncionarParaIdentificarAs3MaioresDeUmaTurmaSemAlunos() {	
	
		relatorio.gerar(notas);
		
		int quantidadeDeNotasEsperadas = 0;
		
		assertEquals(quantidadeDeNotasEsperadas,relatorio.getTop3Notas().size());
	
	}
}


