package br.edu.ifal.academicsistemweb.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifal.academicsistemweb.modelo.Aluno;
import br.edu.ifal.academicsistemweb.modelo.Disciplina;
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;


@Controller
@RequestMapping("/nota")
public class NotaController {
	
	@Autowired
	NotaRepositorio notaRepositorio;
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listNota(ModelMap model) {
		
		List<Notas> notas = notaRepositorio.findAll();
		
		model.addAttribute("notaList", notas);
		
		model.addAttribute("message", "Lista de Notas");
		
		return "nota/list";
	}
	
	@RequestMapping(value = {"/new-{id}-aluno-nota"}, method = RequestMethod.GET)
	public String newNota(@PathVariable("id") Integer id, ModelMap model) {
		
		Notas nota = new Notas();
		
		model.addAttribute("nota", nota);
		
		
		Aluno aluno = alunoRepositorio.getOne(id);
		
		model.addAttribute("aluno", aluno);
		
		Set<Disciplina> listDisciplinas = aluno.getDisciplinas();
		
		model.addAttribute("listDisciplinas", listDisciplinas);
		
		model.addAttribute("edit", false);
		
		return "nota/form";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveNota(@Valid @ModelAttribute Notas nota, BindingResult result,
							ModelMap model) {
		
		if (result.hasErrors()) {
			return "nota/form";
		}
	
		notaRepositorio.saveAndFlush(nota);
		
		model.addAttribute("mensagem", "Nota do(a)" + nota.getAluno() + " cadastrada com sucesso");
		
		return "redirect:/edit-"+nota.getId()+"-nota";
	}
	
	@RequestMapping(value = {"/edit-{id}-nota"}, method = RequestMethod.GET)
	public String editNota(@PathVariable("id") Integer id, ModelMap model) {
		
		Notas nota = notaRepositorio.getOne(id);
		
		model.addAttribute("nota", nota);
		
		model.addAttribute("edit", true);
		
		return "nota/form";
	}
	
	@RequestMapping(value = {"/edit-{id}-nota"}, method = RequestMethod.POST)
	public String updateNota(@Valid Notas nota, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "nota/form";
		}
		
		notaRepositorio.saveAndFlush(nota);
		
		model.addAttribute("mensagem", "Nota do(a)" + nota.getAluno() + " atualizada com sucesso");
		
		return "redirect:/nota/list";
	}
	
	@RequestMapping(value = {"/delete-{id}-nota"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Notas id) {
		
	    notaRepositorio.delete(id);
	      
		return "redirect:/nota/list";
	}
}
