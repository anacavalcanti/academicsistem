package br.edu.ifal.academicsistemweb.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	
	@Column(length = 50, nullable = false)
	private String rua;
	
	@Column(length = 50, nullable = false)
	private String bairro;
	
	@Column (length = 50, nullable = false)
	private String cidade;
	
	@Column (length = 5, nullable = false)
	private Integer ncasa;
	
	public Endereco() {
		super();
	}
	
	public Endereco(String rua, String bairro, String cidade, Integer ncasa) {
		super();
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.ncasa = ncasa;
	}




	public String getrua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getNcasa() {
		return ncasa;
	}
	public void setNcasa(Integer ncasa) {
		this.ncasa = ncasa;
	}
}
