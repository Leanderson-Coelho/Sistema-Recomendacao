package com.ifpb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ifpb.exception.NotaInvalidaException;

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
	
	public boolean novaExperiencia(int nota, int idFilme) throws NotaInvalidaException {
		if(nota>10||nota<0) {
			throw new NotaInvalidaException();
		}
		if(idFilme>0) {
			if(adicionarExperiencia(new Experiencia(nota, idFilme))) {
				ordenarExperiencias();
				return true;
			}
		}
		return false;
	}
	
	private boolean adicionarExperiencia(Experiencia novaEx) {
		if(buscarExp(novaEx.getIdFilme())==null)
			return experiencias.add(novaEx);
		return false;
	}
	
	private Experiencia buscarExp(int idFilme) {
		for(Experiencia e: experiencias) {
			if(e.getIdFilme()==idFilme)
				return e;
		}
		return null;
			
	}
	
	public boolean removerExperiencia(int idFilme) {
		for(Experiencia e: experiencias) {
			if(e.getIdFilme()==idFilme) {
				experiencias.remove(e);
				return true;
			}
		}
		return false;
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
