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
import com.ifpb.exception.NotaInvalidaException;
import com.ifpb.model.Experiencia;
import com.ifpb.model.Filme;
import com.ifpb.model.Genero;
import com.ifpb.model.Usuario;

public class view {
//	private static Usuario u;
	private static final String LOGIN_MASTER = "0";
	private static final String SENHA_MASTER = "0";
	
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		int opcao = 1;
		Usuario usuarioAtual = new Usuario();
		while(opcao!=0) {
			System.out.println("\t\t\t-----INICIAL-----");
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
					}else
						System.out.println("\tLogin inválido\n");
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
					System.out.println("\t\tEmail - Senha");
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
			}else if(opcao==4) {
				try {
					System.out.println("\t\tRemover todos os Usuários__");
					Usuario master = new Usuario();
					ler.nextLine();
					System.out.print("\t\tLogin: ");
					master.setEmail(ler.nextLine());
					System.out.print("\t\tSenha: ");
					master.setSenha(ler.nextLine());			
					if(master.getEmail().equals(LOGIN_MASTER) && master.getSenha().equals(SENHA_MASTER)) {
						if(GerenciaUsuario.apagarTodos())
							System.out.println("Usuarios removidos\n");
						else
							System.out.println("Não foi possível remover os usuários\n");
					}else
						System.out.println("Senha master inválida");
				} catch (ClassNotFoundException | IOException e) {
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
			System.out.println("1: Filmes\t2: Recomendar-me\t3: Menu Avaliação");
			System.out.println("4: Remover conta \t0: Sair");
			opcao=ler.nextInt();
			if(opcao==1) {
				while(opcao!=-1) {
					System.out.println("1: Adicicionar Filme\t2: Ver dados do Filme \t3: Listar Filmes");
					System.out.println("4: Apagar filme \t0: Sair");
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
							System.out.print("Digite o código do filme: ");
							Filme filme = GerenciaFilme.buscarFilmePorCodigo(ler.nextInt());
							if(filme!=null) {
								System.out.println("\tCódigo: "+filme.getCodigo()+"\n"+"\tNome: "+filme.getNome()+"\n"+"\tDireção: "+filme.getDirecao()
								+"\n"+"\tData lançamento: "+filme.getData()+"\n"+"\tIdioma: "+filme.getIdioma()+"\n"+"\tGeneros: "+filme.getGeneros()+"\n");
							}else {
								System.out.println("Filme não encontrado\n");
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
					}else if(opcao == 3) {
						try {
//							GerenciaFilme.getStruct(GerenciaFilme.file).stream().forEach(System.out :: println);
							GerenciaFilme.listarOrganizado();
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
					}else if(opcao==4) {
						System.out.print("Digite o codigo do filme: ");
						try {
							if(GerenciaFilme.remover(ler.nextInt()))
								System.out.println("Filme removido!\n");
							else
								System.out.println("Não foi possível remover o filme\n");
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(opcao==0) {
						opcao=-1;
					}
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
					}else {
						System.out.println("Não foram encontrados filmes para recomendar a partir de suas avaliações\n");
						List<Genero> generosRelevantes = new ArrayList<>();
						System.out.println("1 - AÇÃO \t2 - GUERRA \t3 - ROMANCE \t4 - COMEDIA\n5 - SUSPENSE \t6 - TERROR \t7 - DRAMA \t8 - FICÇÂO CIENTÍFICA\n"
								+ "9 - FANTASIA \t10 - ANIMAÇÃO \t11 - Aventura");
						System.out.print("Informe o código do genero de filme relevante para você: ");
						construirGeneros(generosRelevantes, ler.nextInt());
						int cont = 0;
						while(cont<2) {
							System.out.println("1 - AÇÃO \t2 - GUERRA \t3 - ROMANCE \t4 - COMEDIA\n5 - SUSPENSE \t6 - TERROR \t7 - DRAMA \t8 - FICÇÂO CIENTÍFICA\n"
									+ "9 - FANTASIA \t10 - ANIMAÇÃO \t11 - Aventura");
							System.out.print("Informe outro genero relevante para você ou 0 para sair: ");
							int resposta = ler.nextInt();
							if(resposta>0) {
								cont++;
								construirGeneros(generosRelevantes, resposta);
							}else {
								cont = 3;
							}
						}
						System.out.println("\n");
						List<Filme> filmesIndicar = GerenciaRecomendacao.recomendacaoUsuarioInexperiente(generosRelevantes);
						if(!filmesIndicar.isEmpty()) {
							for(Filme f: filmesIndicar) {
								System.out.println(f.getNome()+" "+f.getGeneros()+"\n");
							}
						}else {
							System.out.println("Não foram encontrados filmes\n");
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}else if(opcao==3) {
				while(opcao!=-1) {
					System.out.println("1: Minhas avaliações \t2: Avaliar Filme \t0: Sair");
					System.out.println("3: Apagar avaliação");
					opcao = ler.nextInt();
					if(opcao==1) {
						listarExperiencia(usuarioAtual);
					}else if(opcao==2) {
						try {
							System.out.println("Nova avaliação");
							GerenciaFilme.listarOrganizado();
							int nota =0, idFilme=0;
							System.out.print("Informe a nota atribuida: ");
							nota = ler.nextInt();
							System.out.print("Informe o código do filme: ");
							ler.nextLine();
							Filme filme = GerenciaFilme.buscarFilmePorCodigo(ler.nextInt());
							if(filme!=null) {
								if(GerenciaUsuario.novaExperiencia(usuarioAtual.getEmail(), filme.getCodigo(), nota)) {
									System.out.println("Avaliação realizada!\n");
								}else {
									System.out.println("Não foi possível avaliar!\n");
								}
							}else {
								System.out.println("Não foi encontrado o filme\n");
							}
						} catch (ClassNotFoundException | IOException | NotaInvalidaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(opcao==3) {
						try {
							listarExperiencia(usuarioAtual);
							System.out.print("Digite o código do filme: ");
							if(GerenciaUsuario.removerExperiencia(usuarioAtual.getEmail(), ler.nextInt())) {
								System.out.println("Avaliação removida!\n");
							}else {
								System.out.println("Não foi possível remover a avaliação!\n");
							}
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(opcao==0) {
						opcao=-1;
					}
				}				
			}else if(opcao==4) {
				ler.nextLine();
				System.out.println("Remover usuário");
				System.out.print("Tem certeza que deseja excluir? 1:SIM  2:NÃO\n-> ");
				if(ler.nextLine().equals("1")) {
					try {
						if(GerenciaUsuario.excluirUsuario(usuarioAtual.getEmail())) {
							System.out.println("Usuário removido");
							break;
						}else
							System.out.println("Não foi possivel remover o usuário");
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		System.out.print("Direção: ");
		filme.setDirecao(ler.nextLine());
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
	
//	1 - AÇÃO \t2 - GUERRA \t3 - ROMANCE \t4 - COMEDIA\n5 - SUSPENSE \t6 - TERROR \t7 - DRAMA \t8 - FICÇÂO CIENTÍFICA\n"
//	+ "9 - FANTASIA \t10 - ANIMAÇÃO \t11 - Aventura
	
	public static void construirGeneros(List<Genero> generos,int codigo) {
		switch(codigo) {
			case 1:
				generos.add(Genero.ACAO);
				break;
			case 2:
				generos.add(Genero.GUERRA);
				break;
			case 3:
				generos.add(Genero.ROMANCE);
				break;
			case 4:
				generos.add(Genero.COMEDIA);
				break;
			case 5:
				generos.add(Genero.SUSPENSE);
				break;
			case 6:
				generos.add(Genero.TERROR);
				break;
			case 7:
				generos.add(Genero.DRAMA);
				break;
			case 8:
				generos.add(Genero.FICCAO_CIENTIFICA);
				break;
			case 9:
				generos.add(Genero.FANTASIA);
				break;
			case 10:
				generos.add(Genero.ANIMACAO);
				break;
			case 11:
				generos.add(Genero.AVENTURA);
				break;
			default:
				System.out.println("opção inválida!\n");
		}
	}
	
	public static void listarExperiencia(Usuario usuarioAtual) {
		List<Experiencia> exp;
		try {
			exp = GerenciaUsuario.buscarUsuario(usuarioAtual.getEmail()).getExperiencias();
//			System.out.println(exp);
			if(!exp.isEmpty()) {
				for(Experiencia e: exp) {
					Filme filme = GerenciaFilme.buscarFilmePorCodigo(e.getIdFilme());
					if(filme!=null) {
						System.out.println("Nota: "+e.getNota()+" Filme: "+filme.getNome()+" Código do filme: "+e.getIdFilme());
					}
				}
				System.out.println("");
			}else {
				System.out.println("Não foram feitas avaliações\n");
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
