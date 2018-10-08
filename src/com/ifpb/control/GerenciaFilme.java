package com.ifpb.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.dao.DaoListGenerico;
import com.ifpb.model.Filme;

public class GerenciaFilme extends DaoListGenerico<Filme>{
	public static File file = new File("binarios/Filmes");
	
	public static int buscarCodigoPorNome(String nome) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		for(Filme f: filmes) {
			if(f.getNome().equals(nome))
				return f.getCodigo();
		}
		return -1;
	}
	
	public static Filme buscarFilmePorNome(String nome) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		for(Filme f: filmes) {
			if(f.getNome().equals(nome)) {
				return f;
			}
		}
		return null;
	}
	
	public static Filme buscarFilmePorCodigo(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		for(Filme f: filmes) {
			if(f.getCodigo()==codigo)
				return f;
		}
		return null;
	}
	
	public static boolean adicionarFilme(Filme novoFilme) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		if(buscarCodigoPorNome(novoFilme.getNome())<0) {
			filmes.add(novoFilme);
			setStruct(filmes, file);
			return true;
		}
		return false;
	}
	
	public static List<Filme> listar() throws FileNotFoundException, ClassNotFoundException, IOException{
		return getStruct(file);
	}
}
