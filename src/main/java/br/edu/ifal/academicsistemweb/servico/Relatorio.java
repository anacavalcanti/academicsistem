package br.edu.ifal.academicsistemweb.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifal.academicsistemweb.modelo.Notas;

public class Relatorio {
	
	private double maiorNota = Double.NEGATIVE_INFINITY;
	private double menorNota = Double.POSITIVE_INFINITY;
	private List<Notas> top3Notas = new ArrayList<Notas>();
	
	public void gerar(List<Notas> notas) {
		for(Notas nota : notas) {
			if(nota.getValor() > maiorNota) {
				maiorNota = nota.getValor();
			}
			if(nota.getValor() < menorNota) {
				menorNota = nota.getValor();	
			}
		}
		
		setarMaioresNotas
		(notas);
	}
	
	private void setarMaioresNotas(List<Notas> notas) {
		List<Notas> notasOrdemDecrescente = notas.stream()
			.sorted(Comparator.comparing(Notas :: getValor))
			.collect(Collectors.toList());
		Collections.reverse(notasOrdemDecrescente);
		
		//System.out.println(notasOrdemDecrescente.toString());
		
		//this.top3Notas = notasOrdemDecrescente.subList(0, 3);
		
		for(int i = 0; i < 3; i++) {
			if(notasOrdemDecrescente.size() > i) {
				this.top3Notas.add(notasOrdemDecrescente.get(i));
			}
			
		}
		
	}

	public double getMaiorNota() {
		return maiorNota;
	}


	public double getMenorNota() {
		return menorNota;
	}

	public List<Notas> getTop3Notas() {
		return top3Notas;
	}

	public void setTop3Notas(List<Notas> top3Notas) {
		this.top3Notas = top3Notas;
	}
	
}
