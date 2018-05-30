package br.edu.ifal.academicsistemweb.controllers;

import java.util.Arrays;
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

import br.edu.ifal.academicsistemweb.modelo.Professor;
import br.edu.ifal.academicsistemweb.modelo.TipoProfessor;
import br.edu.ifal.academicsistemweb.repositorio.ProfessorRepositorio;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorRepositorio professorRepositorio;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String listProfessor(ModelMap model) {
		List<Professor> professor = professorRepositorio.findAll();
		
		model.addAttribute("professorList",professor);
		model.addAttribute("message", "Lista de Professores");
		
		System.out.println("list");
		return "professor/list";
		
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newProfessor(ModelMap model) {
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		model.addAttribute("edit", false);
		
		model.addAttribute("tipoProfessor", Arrays.asList(TipoProfessor.Efetivo));
		
		return "professor/form";
	}
	
	@RequestMapping(value= {"/save"}, method=RequestMethod.POST)
	public String saveProfessor(@Valid @ModelAttribute Professor professor,BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "professor/form";
		}
		
		professorRepositorio.saveAndFlush(professor);
		
		model.addAttribute("mensagem", "Professor " + professor.getNome() + " cadastrado com sucesso");
		
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value = {"/edit-{id}-professor"}, method = RequestMethod.GET)
	public String editProfessor(@PathVariable("id") Integer id, ModelMap model) {
		Professor professor = professorRepositorio.getOne(id);
		model.addAttribute("professor", professor);
		model.addAttribute("edit", true);
		
		model.addAttribute("tipoProfessor", Arrays.asList(TipoProfessor.Subistituto));
		
		return "professor/form";
	}
	
	@RequestMapping(value = {"/edit-{id}-professor"}, method = RequestMethod.POST)
	public String updateProfessor(@Valid Professor professor, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "professor/form";
		}
		
		professorRepositorio.saveAndFlush(professor);
		
		model.addAttribute("mensagem", "Professor" + professor.getNome() + " atualizado com sucesso");
		
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value = {"/delete-{id}-professor"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Professor id) {
		professorRepositorio.delete(id);
		return "redirect:/professor/list";
	}

}

