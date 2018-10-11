package com.ifpb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme implements Serializable{
	private String nome;
	private String direcao;
	private LocalDate data;
	private String idioma;
	private int codigo;
	private List<Genero> generos;
	
	public Filme(int codigo, String nome,List<Genero> generos, String produtora, LocalDate data, String idioma) {
		this.nome = nome;
		this.direcao = produtora;
		this.data = data;
		this.idioma = idioma;
		this.codigo = codigo;
		this.generos = generos;	
	}	
	
	public Filme() {
		//default
		generos = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
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

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", direcao=" + direcao + ", data=" + data + ", idioma=" + idioma + ", codigo="
				+ codigo + ", generos=" + generos + "]";
	}

}
