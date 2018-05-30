package br.edu.ifal.academicsistemweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Curso;
import br.edu.ifal.academicsistemweb.modelo.Escola;
import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.EscolaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;


@Controller
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	EscolaRepositorio escolaRepositorio;
	
	@Autowired
	CursoRepositorio cursoRepositorio;
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@Autowired
	ProfessorRepositorio professorRepositorio;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listEscola(ModelMap model) {
		
		List<Escola> escola = escolaRepositorio.findAll();
		
		model.addAttribute("escolaList", escola);
		
		model.addAttribute("message", "Lista de Escolas");
		System.out.println("list");
		
		return "escola/list";
	}
	
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newEscola(ModelMap model) {
		
		Escola escola = new Escola();
		model.addAttribute("escola", escola);
		model.addAttribute("edit", false);
		
		return "escola/form";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveEscola(@Valid @ModelAttribute Escola escola, BindingResult result,
							ModelMap model) {
		if (result.hasErrors()) {
			return "escola/form";
		}
		
		escolaRepositorio.saveAndFlush(escola);
		
		model.addAttribute("mensagem", "Escola " + escola.getnome() + " cadastrada com sucesso");
		
		return "redirect:/escola/list";
	}
	
	@RequestMapping(value = {"/edit-{id}-escola"}, method = RequestMethod.GET)
	public String editEscola(@PathVariable("id") Integer id, ModelMap model) {
		Escola escola = escolaRepositorio.getOne(id);
		model.addAttribute("escola", escola);
		model.addAttribute("edit", true);
		return "escola/form";
	}
	
	@RequestMapping(value = {"/edit-{id}-escola"}, method = RequestMethod.POST)
	public String updateEscola(@Valid Escola escola, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "escola/form";
		}
		
		escolaRepositorio.saveAndFlush(escola);
		
		model.addAttribute("mensagem", "Escola " + escola.getnome() + " atualizada com sucesso");
		
		return "redirect:/escola/list";
	}
	
	@RequestMapping(value = {"/delete-{id}-escola"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Escola id) {
	      escolaRepositorio.delete(id);
		return "redirect:/escola/list";
	}
	
	@RequestMapping(value = {"/edit-{id}-cursos"}, method = RequestMethod.GET)
	public String editEscolaCursos(@PathVariable("id") Integer id, ModelMap model) {
		
		Escola escola = escolaRepositorio.getOne(id);
		
		model.addAttribute("escola", escola);
		
		List<Curso> cursosAll = cursoRepositorio.findAll();
		
		model.addAttribute("cursosAll", cursosAll);
		
		List<Curso> listCursos = new ArrayList<>();
		
		for(Curso c: escola.getcurso()){
			listCursos.add(c);
		}
		
		model.addAttribute("listCursos", listCursos);
		
		return "escola/form";
	}
	
	@RequestMapping(value = "/addCurso")
	public String addCurso(@RequestParam("escolaId") Integer escolaId,
			@RequestParam("cursoId") Integer cursoId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		Curso curso =  cursoRepositorio.getOne(cursoId);
		
		escola.adicionarCurso(curso);
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-cursos";
	}
	
	@RequestMapping(value="/removeCurso")
	public String removeCurso(@RequestParam("cursoId") Long cursoId,
			@RequestParam("escolaId") Integer escolaId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		escola.getcurso().removeIf(c -> c.getId().equals(cursoId));
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-cursos";
	}
	
	@RequestMapping(value = {"/edit-{id}-professores"}, method = RequestMethod.GET)
	public String editEscolaProfessores(@PathVariable("id") Integer id, ModelMap model) {
		
		Escola escola = escolaRepositorio.getOne(id);
		
		model.addAttribute("escola", escola);
		
		List<Professor> professoresAll = professorRepositorio.findAll();
		
		model.addAttribute("professoresAll", professoresAll);
		
		List<Professor> listProfessores = new ArrayList<>();
		
		for(Professor p: escola.getprofessor()){
			listProfessores.add(p);
		}
		
		model.addAttribute("listProfessores", listProfessores);
		
		return "escola/form";
	}
	
	@RequestMapping(value = "/addProfessor")
	public String addProfessor(@RequestParam("escolaId") Integer escolaId,
			@RequestParam("professorId") Integer professorId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		Professor professor =  professorRepositorio.getOne(professorId);
		
		escola.adicionarProfessor(professor);
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-professores";
	}
	
	@RequestMapping(value="/removeProfessor")
	public String removeProfessor(@RequestParam("professorId") Long professorId,
			@RequestParam("escolaId") Integer escolaId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		escola.getprofessor().removeIf(p -> p.getId().equals(professorId));
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-professores";
	}
	
	@RequestMapping(value = {"/edit-{id}-alunos"}, method = RequestMethod.GET)
	public String editEscolaAlunos(@PathVariable("id") Integer id, ModelMap model) {
		
		Escola escola = escolaRepositorio.getOne(id);
		
		model.addAttribute("escola", escola);
		
		List<Aluno>alunosAll = alunoRepositorio.findAll();
		
		model.addAttribute("alunosAll", alunosAll);
		
		List<Aluno> listAlunos = new ArrayList<>();
		
		for(Aluno a: escola.getAlunos()){
			listAlunos.add(a);
		}
		
		model.addAttribute("listAlunos", listAlunos);
		
		return "escola/form";
	}
	
	@RequestMapping(value = "/addAluno")
	public String addAluno(@RequestParam("escolaId") Integer escolaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		Aluno aluno =  alunoRepositorio.getOne(alunoId);
		
		escola.adicionarAluno(aluno);
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-alunos";
	}
	
	@RequestMapping(value="/removeAluno")
	public String removeAluno(@RequestParam("alunoId") Long alunoId,
			@RequestParam("escolaId") Integer escolaId) {
		
		Escola escola = escolaRepositorio.getOne(escolaId);
		
		escola.getAlunos().removeIf(a -> a.getId().equals(alunoId));
		
		escolaRepositorio.saveAndFlush(escola);
		
		return "redirect:/escola/edit-"+escolaId+"-alunos";
	}


}
