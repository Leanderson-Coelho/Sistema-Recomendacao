package com.ifpb.control;

import java.util.ArrayList;
import java.util.List;

import com.ifpb.model.Filme;

public class GerenciaFilme {
	public static List<Filme> filmes = new ArrayList<>();
	
	public static int buscarCodigoPorNome(String nome) {
		for(Filme f: filmes) {
			if(f.getNome().equals(nome))
				return f.getCodigo();
		}
		return -1;
	}
	
	public static Filme buscarFilmePorNome(String nome) {
		for(Filme f: filmes) {
			if(f.getNome().equals(nome)) {
				return f;
			}
		}
		return null;
	}
	
	public static Filme buscarFilmePorCodigo(int codigo) {
		for(Filme f: filmes) {
			if(f.getCodigo()==codigo)
				return f;
		}
		return null;
	}
	
	public static boolean adicionarFilme(Filme novoFilme) {
		if(buscarCodigoPorNome(novoFilme.getNome())<0)
			return filmes.add(novoFilme);
		return false;
	}
	
	public static List<Filme> listar(){
		return filmes;
	}
}
