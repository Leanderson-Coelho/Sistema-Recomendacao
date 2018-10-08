package com.ifpb.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.ifpb.control.GerenciaFilme;
import com.ifpb.control.GerenciaRecomendacao;
import com.ifpb.control.GerenciaUsuario;
import com.ifpb.model.Experiencia;
import com.ifpb.model.Filme;
import com.ifpb.model.Usuario;

public class view {
	private static Usuario u;
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int opcao = 1;
		Usuario usuarioAtual = new Usuario();
		while(opcao!=0) {
			System.out.println("\t-----INICIAL-----");
			System.out.println("1: Entrar \t2: Novo Usuário \t3: Listar Usuarios\t0: Sair");
			opcao = ler.nextInt();
			if(opcao==1) {
				try {
					ler.nextLine();
					System.out.print("Login: ");
					usuarioAtual.setEmail(ler.nextLine());
					System.out.print("Senha: ");
					usuarioAtual.setSenha(ler.nextLine());
				
					if(GerenciaUsuario.validarUsuario(usuarioAtual.getEmail(), usuarioAtual.getSenha())) {
						menu(ler,opcao,usuarioAtual);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(opcao==2) {
				try {
					Usuario u = new Usuario();
					ler.nextLine();
					System.out.print("Informe o email: ");
					u.setEmail(ler.nextLine());
					System.out.print("Informe a senha: ");
					u.setSenha(ler.nextLine());
					if(GerenciaUsuario.adicionarUsuario(u))
						System.out.println("\n\nUsuario criado!\n");
					else
						System.out.println("\n\nError ao adicionar usuário\n");
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}else if(opcao == 3) {
				try {
					for(Usuario usuario: GerenciaUsuario.listar()) {
						System.out.println("\t\t"+usuario.getEmail()+" - "+usuario.getSenha());
					}
					System.out.println("");
				} catch (FileNotFoundException e) {
					e.getMessage();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(opcao==0){
				System.out.println("\n\nPrograma encerrado!\n");
				break;
			}
		}
	}
	
	public static void menu(Scanner ler,int opcao,Usuario usuarioAtual) {
		while(opcao!=0) {
			System.out.println("1: Filmes\t2: Recomendar-me\t3: Minhas Avaliações \t0: Sair");
			opcao=ler.nextInt();
			if(opcao==1) {
				System.out.println("1: Adicicionar Filme\t2: Ver dados do Filme \t3: Listar Filmes \t0: Sair");
				opcao=ler.nextInt();
				if(opcao==1) {
					ler.nextLine();
					try {
						if(GerenciaFilme.adicionarFilme(construirFilme())) {
							System.out.println("\n\nFilme adicionado!\n\n");
						}else
							System.out.println("Não foi possível adicionar o filme\n");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(opcao==2) {
					try {
						ler.nextLine();
						System.out.print("Digite o nome do filme: ");
						String nome = ler.nextLine();
						System.out.println("\n"+GerenciaFilme.buscarFilmePorNome(nome)+"\n");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(opcao == 3) {
					try {
						System.out.println("Filmes");
//						GerenciaFilme.listar().stream().forEach((f)->{System.out.println(f.getNome()+" - "+f.getProdutora());});
						for(Filme f: GerenciaFilme.listar()) {
							System.out.println(f.getNome()+" - "+f.getProdutora());
						}
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("");
				}else if(opcao==0) {
					opcao= -1;
				}
			}else if(opcao==2) {
				List<Filme> filmes;
				try {
					filmes = GerenciaRecomendacao.recomendacao(usuarioAtual.getEmail());
					if(!filmes.isEmpty()) {
						System.out.println("\n\nFilmes que vc talvez goste\n");
						for(Filme f: filmes) {
							System.out.println(f.getNome()+"\n");
						}
					}else
						System.out.println("\n\nNão foram encontrados filmes para recomendar\n");
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}else if(opcao==3) {
				List<Experiencia> exp;
				try {
					exp = GerenciaUsuario.buscarUsuario(usuarioAtual.getEmail()).getExperiencias();
					System.out.println(exp);
					if(!exp.isEmpty()) {
						for(Experiencia e: exp) {
							System.out.println("Nota: "+e.getNota()+" Filme: "+GerenciaFilme.buscarFilmePorCodigo(e.getIdFilme()).getNome());
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}else if(opcao==0) {
				break;
			}else {
				System.out.println("\nOpção inválida\n");
			}
		}
	}
	
	public static Filme construirFilme() {
		Scanner ler = new Scanner(System.in);
		Filme filme = new Filme();
		System.out.println("Digite as informações do filme");
		System.out.print("Nome: ");
		filme.setNome(ler.nextLine());
		System.out.print("Produtora: ");
		filme.setProdutora(ler.nextLine());
		System.out.println("Informe a data de lançamento:");
		filme.setData(construirData());
		System.out.print("Informe o idioma do Filme: ");
		filme.setIdioma(ler.nextLine());
		System.out.print("Informe o codigo do filme: ");
		filme.setCodigo(ler.nextInt());
		return filme;
	}
	
	public static LocalDate construirData() {
		Scanner ler = new Scanner(System.in);
		int dia,mes,ano;
		System.out.print("Digite o dia: ");
		dia=ler.nextInt();
		System.out.print("Digite o mês: ");
		mes=ler.nextInt();
		System.out.print("Digite o ano: ");
		ano=ler.nextInt();
		return LocalDate.of(ano, mes, dia);
	}
}
