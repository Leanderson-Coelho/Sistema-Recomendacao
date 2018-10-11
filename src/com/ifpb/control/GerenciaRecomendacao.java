package com.ifpb.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ifpb.model.Experiencia;
import com.ifpb.model.Filme;
import com.ifpb.model.Genero;
import com.ifpb.model.Usuario;

public class GerenciaRecomendacao {

//	se a lista de usuarios for vazia verifica pelos itens parecidos
	public static List<Filme> recomendacao(String usuarioSeraRecomendado) throws FileNotFoundException, ClassNotFoundException, IOException {
		Usuario usuarioRecomendacao = GerenciaUsuario.buscarUsuario(usuarioSeraRecomendado);
		if(!usuarioRecomendacao.getExperiencias().isEmpty()) {
			List<Usuario> possiveis = montarGrupo(usuarioRecomendacao);
			List<Experiencia> itensRecomendados = verificarItens(possiveis, usuarioRecomendacao);
			return constroiFilmes(itensRecomendados);
		}
		return new ArrayList<>();
	}
	
	private static List<Usuario> montarGrupo(Usuario usuarioTeraRcmd) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = GerenciaUsuario.getStruct(GerenciaUsuario.file);
		List<Usuario> possiveis = new ArrayList<>();
		for(Usuario usuarioLista: usuarios) {
			for(Experiencia experienciaUsuarioLista: usuarioLista.getExperiencias()) {
				if(!usuarioTeraRcmd.equals(usuarioLista)) {
					for(Experiencia experienciaUsuarioTeraRcmd: usuarioTeraRcmd.getExperiencias()) {
						if(experienciaUsuarioLista.getIdFilme()==experienciaUsuarioTeraRcmd.getIdFilme()) {
							if(experienciaUsuarioLista.getNota()==experienciaUsuarioTeraRcmd.getNota()) {
								possiveis.add(usuarioLista);
							}
						}
					}
				}
			}
		}
		return possiveis;
	}
	
	private static List<Experiencia> verificarItens(List<Usuario> possiveis, Usuario usuarioRcmd) throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Experiencia> expericencias = new ArrayList<>();
		int contadorExperiencias = 0;
		for(Usuario usuarioPossivel: possiveis) {
			for(Experiencia experienciaUsuarioPossivel: usuarioPossivel.getExperiencias()) {
				for(Experiencia experienciaUsuarioRcmd: usuarioRcmd.getExperiencias()) {
					
					if(experienciaUsuarioPossivel.getIdFilme()!=experienciaUsuarioRcmd.getIdFilme()) {
						contadorExperiencias++;
					}
				}
				if(contadorExperiencias==usuarioRcmd.getExperiencias().size() && experienciaUsuarioPossivel.getNota()>=7) {
//					so adicionar se fazer parte do genero que o usuario mais assiste
					if(verificarGenero(experienciaUsuarioPossivel, usuarioRcmd.getEmail()) && !verificaFilmeExistente(expericencias, experienciaUsuarioPossivel.getIdFilme())) {
						expericencias.add(experienciaUsuarioPossivel);	
					}
				}
				contadorExperiencias=0;
			}
		}
		return expericencias;
	}
	
	private static boolean verificaFilmeExistente(List<Experiencia> itens,int idFilme) {
		if(itens!=null) {
			for(Experiencia e: itens) {
				if(e.getIdFilme()==idFilme) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean verificarGenero(Experiencia ExpPossivel, String emailUsuario) throws FileNotFoundException, ClassNotFoundException, IOException {
		Genero generoPerfilUsuario = montarGenerosUsuario(emailUsuario);
		Usuario usuario = GerenciaUsuario.buscarUsuario(emailUsuario);
		if(generoPerfilUsuario!=null && usuario!=null) {
			List<Genero> generosFilme = GerenciaFilme.buscarFilmePorCodigo(ExpPossivel.getIdFilme()).getGeneros();
			for(Genero g: generosFilme) {
				if(g.equals(generoPerfilUsuario)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Genero montarGenerosUsuario(String emailUsuario) throws FileNotFoundException, ClassNotFoundException, IOException{
		Usuario usuario = GerenciaUsuario.buscarUsuario(emailUsuario);
		if(usuario!=null) {
			List<Experiencia> exps = usuario.getExperiencias();
			List<Genero> generosVistos = new ArrayList<>();
			for(Experiencia e: exps) {
				generosVistos.addAll(GerenciaFilme.buscarFilmePorCodigo(e.getIdFilme()).getGeneros());
			}
			int frequenciaMaior = Collections.frequency(generosVistos, generosVistos.get(0));
			Genero generoMaiorFreq = generosVistos.get(0);
			generosVistos = removerGenerosAnalisados(generosVistos, frequenciaMaior, generoMaiorFreq);
			while(generosVistos.size()>0) {
				if(frequenciaMaior < Collections.frequency(generosVistos, generosVistos.get(0))) {
					frequenciaMaior = Collections.frequency(generosVistos, generosVistos.get(0));
					generoMaiorFreq = generosVistos.get(0);
					generosVistos = removerGenerosAnalisados(generosVistos, frequenciaMaior, generoMaiorFreq);
				}else {
					generosVistos = removerGenerosAnalisados(generosVistos, quantidadeGeneroLista(generosVistos, generosVistos.get(0)), generosVistos.get(0));
				}

			}
			System.out.println(generoMaiorFreq);
			return generoMaiorFreq;
		}
		return null;
	}
	
	private static List<Genero> removerGenerosAnalisados(List<Genero> generos,int frequencia ,Genero generoExcluir) {
		for(int i =0;i<frequencia;i++) {
			generos.remove(generoExcluir);
		}
		return generos;
	}
	
	private static int quantidadeGeneroLista(List<Genero> generos,Genero generoContado) {
		int cont = 0;
		for(Genero g: generos) {
			if(g.equals(generoContado)) {
				cont++;
			}
		}
		return cont;
	}
	
	private static List<Filme> constroiFilmes(List<Experiencia> itens) throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Filme> filmes = new ArrayList<>();
		if(itens!=null) {
			for(Experiencia i: itens) {
				Filme filme = GerenciaFilme.buscarFilmePorCodigo(i.getIdFilme());
				if(filme!=null)
					filmes.add(filme);
			}
		}
		return filmes;
	}
}
