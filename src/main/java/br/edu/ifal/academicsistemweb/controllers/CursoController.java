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
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.CursoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;


@Controller
@RequestMapping("/curso")
public class CursoController {
	@Autowired
	CursoRepositorio cursoRepositorio;
	
	@Autowired
	DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listCurso(ModelMap model) {
		
		List<Curso> cursos = cursoRepositorio.findAll();
		
		model.addAttribute("cursoList", cursos);
		
		model.addAttribute("message", "Lista de Cursos");
		
		System.out.println("list");
		
		return "curso/list";
	}
	
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newCurso(ModelMap model) {
		
		Curso curso = new Curso();
		
		model.addAttribute("curso", curso);
		
		
		List<Disciplina> listDisciplinas = new ArrayList<>();
		
		for(Disciplina d: curso.getdisciplina()){
			listDisciplinas.add(d);
		}
		
		model.addAttribute("listDisciplinas", listDisciplinas);
		
		List<Aluno> listAlunos = new ArrayList<>();
		
		for(Aluno a: curso.getaluno()){
			listAlunos.add(a);
		}
		
		model.addAttribute("listAlunos", listAlunos);
		
		model.addAttribute("edit", false);
		
		return "curso/form";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveCurso(@Valid @ModelAttribute Curso curso, BindingResult result,
							ModelMap model) {
		
		System.out.println(curso);
		
		if (result.hasErrors()) {
			return "curso/form";
		}
		
		cursoRepositorio.save(curso);
		
		model.addAttribute("mensagem", "Curso" + curso.getnome() + " cadastrado com sucesso");
		
		return "redirect:/curso/edit-"+curso.getId()+"-curso";
	}
	
	
	@RequestMapping(value = {"/edit-{id}-curso"}, method = RequestMethod.GET)
	public String editCurso(@PathVariable("id") Integer id, ModelMap model) {
		
		Curso curso = cursoRepositorio.getOne(id);
		model.addAttribute("curso", curso);
			
		return "curso/form";
	}
	
	@RequestMapping(value = {"/edit-{id}-curso"}, method = RequestMethod.POST)
	public String updateCurso(@Valid Curso curso, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "curso/form";
		}
		
		cursoRepositorio.saveAndFlush(curso);
		
		model.addAttribute("mensagem", "Curso " + curso.getnome() + " atualizado com sucesso");
		
		return "redirect:/curso/list";
	}
	
	@RequestMapping(value = { "/delete-{id}-curso" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {
		
		cursoRepositorio.deleteById(id);
		
		return "redirect:/curso/list";
	}
	
	
	@RequestMapping(value = {"/edit-{id}-disciplinas"}, method = RequestMethod.GET)
	public String editCursoDisciplinas(@PathVariable("id") Integer id, ModelMap model) {
		
		Curso curso = cursoRepositorio.getOne(id);
		model.addAttribute("curso", curso);
		
		List<Disciplina>disciplinasAll = disciplinaRepositorio.findAll();
		
		model.addAttribute("disciplinasAll", disciplinasAll);
		
		List<Disciplina> listDisciplinas = new ArrayList<>();
		
		for(Disciplina d: curso.getdisciplina()){
			listDisciplinas.add(d);
		}
		
		model.addAttribute("listDisciplinas", listDisciplinas);
		
		return "curso/form";
	}
	
	@RequestMapping(value = "/addDisciplina")
	public String addDisciplina(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("cursoId") Integer cursoId) {
		
		Curso curso = cursoRepositorio.getOne(cursoId);
		
		Disciplina disciplina =  disciplinaRepositorio.getOne(disciplinaId);
		
		curso.adicionarDisciplina(disciplina);
		
		cursoRepositorio.saveAndFlush(curso);
		
		return "redirect:/curso/edit-"+cursoId+"-disciplinas";
	}
	
	@RequestMapping(value="/removeDisciplina")
	public String removeDisciplina(@RequestParam("disciplinaId") Long disciplinaId,
			@RequestParam("cursoId") Integer cursoId) {
		
		Curso curso = cursoRepositorio.getOne(cursoId);
		
		curso.getdisciplina().removeIf(d -> d.getId().equals(disciplinaId));
		
		cursoRepositorio.saveAndFlush(curso);
		
		return "redirect:/curso/edit-"+cursoId+"-disciplinas";
	}
	
	@RequestMapping(value = {"/edit-{id}-alunos"}, method = RequestMethod.GET)
	public String editCursoAlunos(@PathVariable("id") Integer id, ModelMap model) {
		
		Curso curso = cursoRepositorio.getOne(id);
		model.addAttribute("curso", curso);
		
		List<Aluno>alunosAll = alunoRepositorio.findAll();
		
		model.addAttribute("alunosAll", alunosAll);
		
		List<Aluno> listAlunos = new ArrayList<>();
		
		for(Aluno a: curso.getaluno()){
			listAlunos.add(a);
		}
		
		model.addAttribute("listAlunos", listAlunos);
		
		return "curso/form";
	}
	
	@RequestMapping(value = "/addAluno")
	public String addAluno(@RequestParam("cursoId") Integer cursoId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Curso curso = cursoRepositorio.getOne(cursoId);
		
		Aluno aluno =  alunoRepositorio.getOne(alunoId);
		
		curso.adicionarAluno(aluno);
		
		cursoRepositorio.saveAndFlush(curso);
		
		return "redirect:/curso/edit-"+cursoId+"-alunos";
	}
	
	@RequestMapping(value="/removeAluno")
	public String removeAluno(@RequestParam("alunoId") Long alunoId,
			@RequestParam("cursoId") Integer cursoId) {
		
		Curso curso = cursoRepositorio.getOne(cursoId);
		
		curso.getaluno().removeIf(a -> a.getId().equals(alunoId));
		
		cursoRepositorio.saveAndFlush(curso);
		
		return "redirect:/curso/edit-"+cursoId+"-alunos";
	}
	
}