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
	private static final Float NOTAMEDIARELEVANTE = 8.5f;

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
	
	/**
	 * Monta o grupo de usuários que fizeram avaliações positivas aos mesmos filmes que o usuário que terá uma recomendação.
	 * Se um usuário tem experiencias com os mesmos filmes e as notas atribuidas são maior ou igual a nota do usuário que terá recomendação, este é incluido na lista.
	 * @param usuarioTeraRcmd Recebe o usuário que terá uma recomendação.
	 * @return Lista de usuários com experiencias semelhantes.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static List<Usuario> montarGrupo(Usuario usuarioTeraRcmd) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = GerenciaUsuario.getStruct(GerenciaUsuario.file);
		List<Usuario> possiveis = new ArrayList<>();
		for(Usuario usuarioLista: usuarios) {
			for(Experiencia experienciaUsuarioLista: usuarioLista.getExperiencias()) {
				if(!usuarioTeraRcmd.equals(usuarioLista)) {
					for(Experiencia experienciaUsuarioTeraRcmd: usuarioTeraRcmd.getExperiencias()) {
						if(experienciaUsuarioLista.getIdFilme()==experienciaUsuarioTeraRcmd.getIdFilme()) {
							if(experienciaUsuarioLista.getNota()>=experienciaUsuarioTeraRcmd.getNota()) {
								possiveis.add(usuarioLista);
							}
						}
					}
				}
			}
		}
		return possiveis;
	}
	
	/**
	 * Monta uma lista de experiencias que guarda os id's de filmes que podem ser interessante para o usuário.
	 * Se o id do filme que pertence a uma experiencia, obtida do grupo de usuários com interesses semelhantes, não é igual a nenhum outro id de filmes das 
	 * experiencias do usuário e se a nota feito pelo usuário, do grupo montado, for maior que 7, esta experiencia é incluida na lista para retorno.
	 * @param possiveis Recebe um grupo de usuários que compartilham da mesma experiencia que o usuário.
	 * @param usuarioRcmd Recebe o usuário que terá uma recomendação.
	 * @return Retorna uma lista de experiencias que contem os id's dos filmes que não foram vistos pelo usuário e pode ser interessante para o mesmo.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
	
	/**
	 * Verifica se um filme está em uma lista de experiencias.
	 * @param itens Lista que contem experiecias.
	 * @param idFilme Id do filme que será usado para procurar na lista de <b>itens</b>.
	 * @return <b>true</b> se o id do filme pertence a lista caso contrário <b>false</b>. 
	 */
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
	
	/**
	 * Verifica se um filme tem, entre todos associado ao mesmo, um gênero igual ao gênero mais visto pelo usuário passado.
	 * @param ExpPossivel Recebe a experiencia que contem o id do filme que será analisado.
	 * @param emailUsuario Recebe o email do usuário que será analisado para obter o gêneto mais visto.
	 * @return <b>true</b> caso o filme, encontrado na experiencia passada, tenha pelo menos um dos gêneros igual ao obtido na análise do usuário.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
	
	/**
	 * Verifica qual o gênero mais visto pelo usuário.
	 * Cada genero visto pelo usuário é colocado em um lista com repetições de genero, é feito um processo de atribuição do primeiro item da lista como genero
	 * mais visto e armazenado a frequência que o mesmo aparece na lista, depois desta etapa, todos as repetições deste item que se encontrava na primeira posição
	 * são removidas e verificado o novo primeiro item, se este tem frequencia maior do que a última armazenada o genero mais visto é igual ao novo primeiro item.
	 * @param emailUsuario Recebe o email do usuário.
	 * @return Retorna o gênero mais visto pelo usuário.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Genero montarGenerosUsuario(String emailUsuario) throws FileNotFoundException, ClassNotFoundException, IOException{
		Usuario usuario = GerenciaUsuario.buscarUsuario(emailUsuario);
		if(usuario!=null) {
			List<Experiencia> exps = usuario.getExperiencias();
			List<Genero> generosVistos = new ArrayList<>();
//			adiciona todos os generos vistos pelo usuario na lista generosVistos. Podem ser adicionados generos repetidos.
			for(Experiencia e: exps) {
				generosVistos.addAll(GerenciaFilme.buscarFilmePorCodigo(e.getIdFilme()).getGeneros());
			}
//			frequenciaMaior recebe o quantidade de vezes que o primeiro genero da lista aparece
//			generoMaiorFreq recebe o primeiro genero da lista
			int frequenciaMaior = Collections.frequency(generosVistos, generosVistos.get(0));
			Genero generoMaiorFreq = generosVistos.get(0);
//			remove todas os generos iguais ao primeiro da lista. Começando assim, a esvaziar a lista!
			generosVistos = removerGenerosAnalisados(generosVistos, frequenciaMaior, generoMaiorFreq);
			while(generosVistos.size()>0) {
//				se a frequencia do novo primeiro genero da lista é maior que o ultimo(que ja foi removido da lista) é feito o mesmo processo de remoção e atribuição.
//				se não, apenas remove todos os generos iguais ao primeiro da lista(incluindo o primeiro)
				if(frequenciaMaior < Collections.frequency(generosVistos, generosVistos.get(0))) {
					frequenciaMaior = Collections.frequency(generosVistos, generosVistos.get(0));
					generoMaiorFreq = generosVistos.get(0);
					generosVistos = removerGenerosAnalisados(generosVistos, frequenciaMaior, generoMaiorFreq);
				}else {
					generosVistos = removerGenerosAnalisados(generosVistos, quantidadeGeneroLista(generosVistos, generosVistos.get(0)), generosVistos.get(0));
				}

			}
			return generoMaiorFreq;
		}
		return null;
	}
	
	/**
	 * Remove os gêneros já analisados na função <b>montarGeneroUsuario</b>. Remove o gênero <b>generoExcluir</b> da lista com número de vezes igual a <b>frequencia</b>.
	 * @param generos Recebe a lista que terá as remoções.
	 * @param frequencia Recebe o número de vezes que o gênero que será removido aparece na lista.
	 * @param generoExcluir Recebe o gênero que será excluido da lista <b>generos</b>.
	 * @return
	 */
	private static List<Genero> removerGenerosAnalisados(List<Genero> generos,int frequencia ,Genero generoExcluir) {
		for(int i =0;i<frequencia;i++) {
			generos.remove(generoExcluir);
		}
		return generos;
	}
	
	/**
	 * Verifica a quantidade de vezes que um gênero aparece em uma lista. Utilizado na função <b>montarGeneroUsuario</b>.
	 * @param generos Recebe a lista que será verificada.
	 * @param generoContado Recebe o gênero que será usado para analisar sua frequência na lista.
	 * @return Quantidade de vezes que o <b>generoContado</b> aparece na lista.
	 */
	private static int quantidadeGeneroLista(List<Genero> generos,Genero generoContado) {
		int cont = 0;
		for(Genero g: generos) {
			if(g.equals(generoContado)) {
				cont++;
			}
		}
		return cont;
	}
	
	/**
	 * Controi uma lista de filmes apartir de uma lista de experiencias.
	 * @param itens Recebe uma lista de experiencias, onde cada experiencia contem um id de filme.
	 * @return Uma lista de Filmes.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
	
	/**
	 * 
	 * @param generosRelevantes
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<Filme> recomendacaoUsuarioInexperiente(List<Genero> generosRelevantes) throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println(generosRelevantes);
		List<Filme> filmesPossiveis = constroiMelhoresFilmesPorNota();
		List<Filme> recomendarFilmes = new ArrayList<>();
		if(!filmesPossiveis.isEmpty()) {
			for(int i = 0; i<filmesPossiveis.size();i++) {
				if(verificaFilmeContemGenero(filmesPossiveis.get(i), generosRelevantes)) {
					recomendarFilmes.add(filmesPossiveis.get(i));
				}
			}
		}
		return recomendarFilmes;
	}
	
	private static boolean verificaFilmeContemGenero(Filme filme, List<Genero> generos) {
		if(!generos.isEmpty() && filme!=null) {
			for(Genero generosFilme: filme.getGeneros()) {
				for(Genero gVerificar: generos) {
					if(generosFilme.equals(gVerificar)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static List<Filme> constroiMelhoresFilmesPorNota() throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Filme> filmes = GerenciaFilme.getStruct(GerenciaFilme.file);
		List<Usuario> usuarios = GerenciaUsuario.getStruct(GerenciaUsuario.file);
		List<Filme> melhoresFilmesPorNota = new ArrayList<>();
		Float somatorioNotas = 0f;
		Float qtdAvaliacoes = 0f;
		if(!filmes.isEmpty()) {
			for(Filme f: filmes) {
				if(!usuarios.isEmpty()) {
					for(Usuario u:usuarios) {
						if(!u.getExperiencias().isEmpty()) {
							for(Experiencia e: u.getExperiencias()) {
								if(e.getIdFilme()==f.getCodigo()) {
									somatorioNotas+=e.getNota();
									qtdAvaliacoes++;
								}
							}
						}
					}
					if(somatorioNotas>0) {
						if(somatorioNotas/qtdAvaliacoes >= NOTAMEDIARELEVANTE) {
							melhoresFilmesPorNota.add(f);
						}
					}
				}
				somatorioNotas =0f;
				qtdAvaliacoes =0f;
			}
		}
		return melhoresFilmesPorNota;
	}
}
