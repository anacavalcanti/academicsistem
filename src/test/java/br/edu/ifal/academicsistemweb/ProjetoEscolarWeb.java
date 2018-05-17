package br.edu.ifal.academicsistemweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import org.springframework.transaction.annotation.Transactional;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Assunto;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Endereco;
import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.AssuntoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.BoletimRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.EscolaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoEscolarWeb {
	
	@Autowired
	private CursoRepositorio cursoRepository;
	
	@Autowired
	private EscolaRepositorio escolaRepository;
	
	@Autowired
	private AlunoRepositorio alunoRepository;
	
	@Autowired
	private ProfessorRepositorio professorRepository;
	
	@Autowired
	private NotaRepositorio notaRepository;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepository;
	
	@Autowired
	private AssuntoRepositorio assuntoRepository;
	
	@Autowired
	private BoletimRepositorio boletimRepository;

	@Test
	@Transactional
	public void insertAlunoTest() {
		
		Aluno aluno = new Aluno();
		aluno .setNome("Pedro");
		aluno .setMatricula("222");
		aluno .setEndereco(new Endereco("", "", "",1));
		aluno .getTelefones().add("(82)9.9925-9987");
		aluno .getTelefones().add("(82)9.8823-2514");
		
		alunoRepository.save(aluno);
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Ana");
		aluno1.setMatricula("123");
		aluno1.setEndereco(new Endereco("", "", "",1));
		aluno1.getTelefones().add("(82)9.9821-6987");
		aluno1.getTelefones().add("(82)9.9623-2214");
		
		alunoRepository.save(aluno1);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Júlia");
		aluno2.setMatricula("425");
		aluno2.setEndereco(new Endereco("", "", "",1));
		aluno2.getTelefones().add("(82)9.9785-6987");
		aluno2.getTelefones().add("(82)9.9254-2814");
		
		alunoRepository.save(aluno2);
	}
	
	@Test
	@Transactional
	public void insertProfessorTest() {
		
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
		professor2.setTipoProfessor(TipoProfessor.Efetivo);;
		
		professorRepository.save(professor2);
	}
	
	@Test
	@Transactional
	public void insertAssuntoTest() {
		Assunto assunto = new Assunto();
		assunto.setnome("Experiência do usuário");
		assuntoRepository.save(assunto);
		
		Assunto assunto1 = new Assunto();
		assunto1.setnome("Técninas de qualidade de software");
		assuntoRepository.save(assunto1);
		
		Assunto assunto2 = new Assunto();
		assunto2.setnome("Internet das coisas");
		assuntoRepository.save(assunto2);
	}
	
	@Test
	@Transactional
	public void insertDisciplinaTest() {
		
		Professor professor = professorRepository.getOne(1);
		
		Aluno aluno = alunoRepository.getOne(1);
		
		Assunto assunto = assuntoRepository.findAll().get(0);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("PIU");
		disciplina.getAssuntos().add(assunto);
		disciplina.getProfessor().add(professor);
		disciplina.getAlunos().add(aluno);
		
		disciplinaRepository.saveAndFlush(disciplina);
		
		
		
		
		Professor professor1 = professorRepository.getOne(2);
		
		Aluno aluno1 = alunoRepository.getOne(3);
		
		Assunto assunto1 = assuntoRepository.getOne(1);
		
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("GQS");
		disciplina1.getAssuntos().add(assunto1);
		disciplina1.getProfessor().add(professor1);
		disciplina1.getAlunos().add(aluno1);
		
		disciplinaRepository.saveAndFlush(disciplina1);
	}
	
}
