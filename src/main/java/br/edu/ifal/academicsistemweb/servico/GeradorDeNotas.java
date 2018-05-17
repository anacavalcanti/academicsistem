package br.edu.ifal.academicsistemweb.servico;

import br.edu.ifal.academicsistemweb.modelo.Notas;

public class GeradorDeNotas {
	
public boolean setarNota(Notas nota) {
		
		if(nota.getValor() < 0 || nota.getValor() > 10) {
			return false;
		}else if(nota.getDisciplina() == null || nota.getAluno() == null) {
			return false;	
		}else {
			return true;
		}
	}
	
}

