package com.ifpb.model;

import java.io.Serializable;

public class Experiencia implements Comparable<Experiencia>, Serializable{
	private int nota;
	private int idFilme;
	private static int id;
	private int codigo;
	
	public Experiencia(int nota, int idFilme) {
		this.nota = nota;
		this.idFilme = idFilme;
		codigo = ++id;
	}

	@Override
	public int compareTo(Experiencia arg0) {
		return arg0.getNota() - nota;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Experiencia [nota=" + nota + ", idFilme=" + idFilme + ", codigo=" + codigo + "]";
	}
	
}
