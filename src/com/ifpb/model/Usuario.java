package com.ifpb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Usuario implements Serializable {
	private String email;
	private String senha;
	private List<Experiencia> experiencias;
	
	public Usuario(String email,String senha) {
		this.email = email;
		this.senha = senha;
		experiencias = new ArrayList<>();
	}
	
	public Usuario() {
		//default
		experiencias = new ArrayList<>();
	}
	
	public boolean novaExperiencia(int nota, int idFilme) {
		if(idFilme>0) {
			adicionarExperiencia(new Experiencia(nota, idFilme));
			ordenarExperiencias();
			return true;
		}
		return false;
	}
	
	private boolean adicionarExperiencia(Experiencia novaEx) {
		if(buscarExp(novaEx.getCodigo())==null)
			return experiencias.add(novaEx);
		return false;
	}
	
	private Experiencia buscarExp(int codigo) {
		for(Experiencia e: experiencias) {
			if(e.getCodigo() == codigo)
				return e;
		}
		return null;
			
	}
	
	public void ordenarExperiencias() {
		Collections.sort(experiencias);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", experiencias=" + experiencias + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
}
