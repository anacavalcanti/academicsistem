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
import br.edu.ifal.academicsistemweb.modelo.Notas;
import br.edu.ifal.academicsistemweb.repositorio.AlunoRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.DisciplinaRepositorio;
import br.edu.ifal.academicsistemweb.repositorio.NotaRepositorio;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	
	@Autowired
	DisciplinaRepositorio DisciplinaRepositorio;
	
	
	@Autowired
	AlunoRepositorio alunoRepository;
	
	@Autowired
	NotaRepositorio notaRepositorio;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listAluno(ModelMap model) {
		
		List<Aluno> alunos = alunoRepository.findAll();
		
		model.addAttribute("alunosList", alunos);
		
		model.addAttribute("message", "Lista de alunos");
		System.out.println("list");
		
		return "aluno/list";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAluno(ModelMap model) {
		
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", false);
		
		return "aluno/form";
	}
	
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String saveAluno(@Valid @ModelAttribute Aluno aluno, BindingResult result,
							ModelMap model) {
		if (result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.save(aluno);
		
		model.addAttribute("mensagem", "Aluno " + aluno.getNome() + " registrado com sucesso");
		
		return "redirect:/aluno/list"+aluno.getId()+"-aluno";
	}
	
	@RequestMapping(value = { "/edit-{id}-aluno" }, method = RequestMethod.GET)
	public String editAluno(@PathVariable("id") Integer id, ModelMap model) {
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		
		return "aluno/form";
	}
	
	@RequestMapping(value = { "/edit-{id}-aluno" }, method = RequestMethod.POST)
	public String updateAluno(@Valid Aluno aluno, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.saveAndFlush(aluno);
		
		model.addAttribute("mensagem", "Aluno " + aluno.getNome() + " atualizado com sucesso");
		
		return "redirect:/aluno/list";
	}
	
	@RequestMapping(value = { "/delete-{id}-aluno" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {
		alunoRepository.deleteById(id);
		return "redirect:/aluno/list";
	}
	@RequestMapping(value="/deletarContato")
	public String deletarContato(@RequestParam("tel") String tel,
			@RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		List<String> contatosAll = aluno.getTelefones();
		
		contatosAll.remove(tel);
		
		alunoRepository.saveAndFlush(aluno);

		return "redirect:/aluno/edit-"+alunoId+"-aluno";
	}
	
	
	@RequestMapping(value="/adicionarContato", method = RequestMethod.POST)
	public String addContato(final String telefone, @RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		aluno.getTelefones().add(telefone);
			
		alunoRepository.saveAndFlush(aluno);

		return "redirect:/aluno/edit-"+alunoId+"-aluno";
	}
	

	@RequestMapping(value = {"/edit-{id}-aluno-disciplinas"}, method = RequestMethod.GET)
	public String editAlunoDisciplinas(@PathVariable("id") Integer id, ModelMap model) {
		
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		
		List<Disciplina>disciplinasAll = DisciplinaRepositorio.findAll();
		
		model.addAttribute("disciplinasAll", disciplinasAll);
		
		List<Disciplina> listDisciplinas = new ArrayList<>();
		
		for(Disciplina d: aluno.getDisciplinas()){
			listDisciplinas.add(d);
		}
		
		model.addAttribute("listDisciplinas", listDisciplinas);
		
		return "aluno/form";
	}
	
	@RequestMapping(value = "/addDisciplina")
	public String addDisciplina(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		Disciplina disciplina =  DisciplinaRepositorio.getOne(disciplinaId);
		
		aluno.adicionarDisciplina(disciplina);
		
		disciplina.adicionarAluno(aluno);
		
		alunoRepository.saveAndFlush(aluno);
		
		DisciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/aluno/edit-"+alunoId+"-aluno";
	}
	
	@RequestMapping(value="/removeDisciplina")
	public String removeDisciplina(@RequestParam("disciplinaId") Integer disciplinaId,
			@RequestParam("alunoId") Integer alunoId) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		
		Disciplina disciplina = DisciplinaRepositorio.getOne(disciplinaId);
		
		aluno.getDisciplinas().removeIf(d -> d.getId().equals(disciplinaId));
		
		disciplina.getAlunos().removeIf(a -> a.getId().equals(alunoId));
		
		alunoRepository.saveAndFlush(aluno);
		
		DisciplinaRepositorio.saveAndFlush(disciplina);
		
		return "redirect:/aluno/edit-"+alunoId+"-aluno";
	}
	
	@RequestMapping(value = {"/edit-{id}-aluno-notas"}, method = RequestMethod.GET)
	public String editAlunoNotas(@PathVariable("id") Integer id, ModelMap model) {
		
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		
		List<Notas> notasAll = notaRepositorio.findAll();
		
		List<Notas>listNotas = new ArrayList<>();
		
		for(Notas nota : notasAll) {
			if(nota.getAluno().getId() == (aluno.getId())) {
				listNotas.add(nota);
			}
		}
		
		model.addAttribute("listNotas", listNotas);
		
		return "aluno/form";
	}
	
	@RequestMapping(value = {"/editNota"}, method = RequestMethod.GET)
	public String updateNota(@RequestParam("notaId") Long notaId, @RequestParam("alunoId") Integer alunoId,  ModelMap model) {
		
		Aluno aluno = alunoRepository.getOne(alunoId);
		model.addAttribute("aluno", aluno);
		
		List<Notas> notasAll = notaRepositorio.findAll();
		
		List<Notas>listNotas = new ArrayList<>();
		
		for(Notas nota : notasAll) {
			if(nota.getAluno().getId() == (aluno.getId())) {
				listNotas.add(nota);
			}
		}
		
		model.addAttribute("listNotas", listNotas);
		
		return "aluno/form";
	}
	
	@RequestMapping(value = {"/editNota"}, method = RequestMethod.POST)
	public String saveNota(@RequestParam("notaId") Long notaId, @RequestParam("alunoId") Long alunoId,
			List<Notas>listNotas, BindingResult result, ModelMap model) {
		
		System.out.println(listNotas);
		
		for(Notas nota : listNotas ) {
			
			notaRepositorio.saveAndFlush(nota);
		}
		
		return "aluno/form";
	}
	
	
	@RequestMapping(value="/removeNota")
	public String removeNota(@RequestParam("notaId") Integer notaId,
			@RequestParam("alunoId") Long alunoId) {
		
		Notas nota = notaRepositorio.getOne(notaId);
		
		notaRepositorio.delete(nota);
		
		return "redirect:/aluno/edit-"+alunoId+"-aluno";
	}

}
