package com.ifpb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Filme implements Serializable{
	private String nome;
	private String produtora;
	private LocalDate data;
	private String idioma;
	private int codigo;
	
	public Filme(String nome, String produtora, LocalDate data, String idioma, int codigo) {
		this.nome = nome;
		this.produtora = produtora;
		this.data = data;
		this.idioma = idioma;
		this.codigo = codigo;
	}
	
	public Filme() {
		//default
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", produtora=" + produtora + ", data=" + data + ", idioma=" + idioma
				+ ", codigo=" + codigo + "]";
	}

}
