package com.ifpb.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.model.Experiencia;
import com.ifpb.model.Filme;
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
	
	private static List<Experiencia> verificarItens(List<Usuario> possiveis, Usuario usuarioRcmd){
		List<Experiencia> expericencias = new ArrayList<>();
		int contadorExperiencias = 0;
		for(Usuario usuarioPossivel: possiveis) {
			for(Experiencia experienciaUsuarioPossivel: usuarioPossivel.getExperiencias()) {
				for(Experiencia experienciaUsuarioRcmd: usuarioRcmd.getExperiencias()) {
					if(experienciaUsuarioPossivel.getIdFilme()!=experienciaUsuarioRcmd.getIdFilme()) {
						contadorExperiencias++;
					}
				}
				if(contadorExperiencias==usuarioRcmd.getExperiencias().size()&&experienciaUsuarioPossivel.getNota()>=7) {
					expericencias.add(experienciaUsuarioPossivel);
				}
				contadorExperiencias=0;
			}
		}
		return expericencias;
	}
	
	private static List<Filme> constroiFilmes(List<Experiencia> itens){
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
