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
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;


@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
	@Autowired
	DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@Autowired
	ProfessorRepositorio professorRepositorio;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listDisciplina(ModelMap model) {
		
		List<Disciplina> disciplina = disciplinaRepositorio.findAll();
		
		model.addAttribute("disciplinaList", disciplina);
		
		model.addAttribute("message", "Lista de Disciplinas");
		
		System.out.println("list");
		
		return "disciplina/list";
	}
	
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newDisciplina(ModelMap model) {
		
		Disciplina disciplina = new Disciplina();
		
		model.addAttribute("disciplina", disciplina);
		
		model.addAttribute("edit", false);
		
		return "disciplina/form";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveDisciplina(@Valid @ModelAttribute Disciplina disciplina, BindingResult result,
							ModelMap model) {
		
		System.out.println(disciplina);
		
		if (result.hasErrors()) {
			return "disciplina/form";
		}
		
		disciplinaRepositorio.save(disciplina);
		
		model.addAttribute("mensagem", "Disciplina " + disciplina.getNome() + " cadastrada com sucesso");
		
		return "redirect:/disciplina/edit-"+disciplina.getId()+"-disciplina";
	}
	
	@RequestMapping(value = {"/edit-{id}-disciplina"}, method = RequestMethod.GET)
	public String editDisciplina(@PathVariable("id") Integer id, ModelMap model) {
		
		Disciplina disciplina= disciplinaRepositorio.getOne(id);
		model.addAttribute("disciplina", disciplina);
			
		return "disciplina/form";
	}
	
	@RequestMapping(value = {"/edit-{id}-disciplina"}, method = RequestMethod.POST)
	public String updateDisciplina(@Valid Disciplina disciplina, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "disciplina/form";
		}
		
		disciplinaRepositorio.saveAndFlush(disciplina);
		
		model.addAttribute("mensagem", "Disciplina " + disciplina.getNome() + " atualizada com sucesso");
		
		return "redirect:/disciplina/list";
	}
	
	@RequestMapping(value = { "/delete-{id}-disciplina" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Disciplina id) {
		
		disciplinaRepositorio.delete(id);
		
		return "redirect:/disciplina/list";
	}
	
	
	@RequestMapping(value = {"/edit-{id}-alunos"}, method = RequestMethod.GET)
	public String editDisciplinaAlunos(@PathVariable("id") Integer id, ModelMap model) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(id);
		
		model.addAttribute("disciplina", disciplina);
		
		List<Aluno>alunosAll = alunoRepositorio.findAll();
		
		model.addAttribute("alunosAll", alunosAll);
		
		List<Aluno> listAlunos = new ArrayList<>();
		
		for(Aluno a: disciplina.getAlunos()){
			listAlunos.add(a);
		}
		
		model.addAttribute("listAlunos", listAlunos);
		
		return "disciplina/form";
	}
	
	@RequestMapping(value = "/addAluno")
	public String addAluno(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(disciplinaId);
		
		Aluno aluno =  alunoRepositorio.getOne(alunoId);
		
		disciplina.adicionarAluno(aluno);
		
		disciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/disciplina/edit-"+disciplinaId+"-alunos";
	}
	
	@RequestMapping(value="/removeAluno")
	public String removeAluno(@RequestParam("alunoId") Long alunoId,
			@RequestParam("disciplinaId") Integer disciplinaId) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(disciplinaId);
		
		disciplina.getAlunos().removeIf(a -> a.getId().equals(alunoId));
		
		disciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/disciplina/edit-"+disciplinaId+"-alunos";
	}
	
	@RequestMapping(value = {"/edit-{id}-professores"}, method = RequestMethod.GET)
	public String editDisciplinaProfessores(@PathVariable("id") Integer id, ModelMap model) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(id);
		
		model.addAttribute("disciplina", disciplina);
		
		List<Professor> professoresAll = professorRepositorio.findAll();
		
		model.addAttribute("professoresAll", professoresAll);
		
		List<Professor> listProfessores = new ArrayList<>();
		
		for(Professor p: disciplina.getProfessor()){
			listProfessores.add(p);
		}
		
		model.addAttribute("listProfessores", listProfessores);
		
		return "disciplina/form";
	}
	
	@RequestMapping(value = "/addProfessor")
	public String addProfessor(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("professorId") Integer professorId) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(disciplinaId);
		
		Professor professor =  professorRepositorio.getOne(professorId);
		
		disciplina.adicionarProfessor(professor);
		
		disciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/disciplina/edit-"+disciplinaId+"-professores";
	}
	
	@RequestMapping(value="/removeProfessor")
	public String removeProfessor(@RequestParam("professorId") Long professorId,
			@RequestParam("disciplinaId") Integer disciplinaId) {
		
		Disciplina disciplina = disciplinaRepositorio.getOne(disciplinaId);
		
		disciplina.getProfessor().removeIf(p -> p.getId().equals(professorId));
		
		disciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/disciplina/edit-"+disciplinaId+"-professores";
	}
	
}


