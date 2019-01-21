package com.ifpb.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import com.ifpb.dao.DaoListGenerico;
import com.ifpb.model.Filme;

public class GerenciaFilme extends DaoListGenerico<Filme>{
	public static File file = new File("binarios/Filmes");
	//espaçamento do listarOrganizado
	private final static int ESPACAMENTO_BASICO = 5;
	
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
	
	public static int buscarIndicePorCodigo(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		if(!filmes.isEmpty()) {
			for(int i = 0;i<filmes.size();i++) {
				if(filmes.get(i).getCodigo()==codigo)
					return i;
			}
		}
		return -1;
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
	
	public static boolean remover(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		if(buscarFilmePorCodigo(codigo)!=null) {
			if(filmes.remove(buscarIndicePorCodigo(codigo))!=null) {
				setStruct(filmes, file);
				return true;
			}
		}
		return false;
	}
	
	public static void listarOrganizado() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Filme> filmes = getStruct(file);
		if(!filmes.isEmpty()) {
			int tamanhoNomeMaior = filmes.get(0).getNome().length();
			for(Filme f: filmes) {
				if(f.getNome().length()>tamanhoNomeMaior)
					tamanhoNomeMaior = f.getNome().length();
			}
			String cabecalho = "Código\t\tNome";
			for(int i = 0;i<tamanhoNomeMaior+1;i++) {
				cabecalho+=" ";
			}
			cabecalho+="Direção";
			System.out.print(cabecalho+"\n\n");
			for(Filme f: filmes) {
				System.out.print(f.getCodigo()+"\t->\t"+f.getNome());
				for(int i = 0;i<diferencaTamanhoString(tamanhoNomeMaior, f.getNome())+ESPACAMENTO_BASICO;i++) {
					System.out.print(".");
				}
				System.out.println(f.getDirecao());
			}
		}
	}
	
	private static int diferencaTamanhoString(int tamanhoNomeMaior,String sMenor) {
		return tamanhoNomeMaior - sMenor.length();
	}
	
	private boolean validar(Filme filme) {
//		if(filme.get)
		return true;
	}
}
