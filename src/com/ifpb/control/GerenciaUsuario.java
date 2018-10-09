package com.ifpb.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ifpb.dao.DaoListGenerico;
import com.ifpb.exception.NotaInvalidaException;
import com.ifpb.model.Experiencia;
import com.ifpb.model.Usuario;

public class GerenciaUsuario extends DaoListGenerico<Usuario>{
	public static File file = new File("binarios/Usuarios");
	private static int contador = 0;
	
	public static List<Usuario> recursoEstrutura() throws FileNotFoundException, ClassNotFoundException, IOException {
		while(contador>0) {System.out.println("Lista usuários ocupada");};
		return getStruct(file);
	}
	
	public static boolean validarUsuario(String email, String senha) throws FileNotFoundException, ClassNotFoundException, IOException {
		Usuario u = buscarUsuario(email);
		if(u!=null) {
			if(u.getSenha().equals(senha))
				return true;
		}
		return false;
	}
	
	public static boolean adicionarUsuario(Usuario novoUsuario) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = getStruct(file);
//		contador++;
		if(buscarUsuario(novoUsuario.getEmail())==null) {
			usuarios.add(novoUsuario);
			setStruct(usuarios, file);
//			contador--;
			return true;
		}
//		contador--;
		return false;
	}
	
	public static Usuario buscarUsuario(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = getStruct(file);
		if(!usuarios.isEmpty()) {
			for(Usuario u: usuarios) {
				if(u.getEmail().equals(email))
					return u;
			}
		}
		return null;
	}
	
	public static boolean excluirUsuario(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = getStruct(file);
		if(!usuarios.isEmpty()) {
			usuarios.remove(buscarUsuario(email));
			GerenciaUsuario.setStruct(usuarios, file);
			return true;
		}
		return false;
	}
	
	public static List<Usuario> listar() throws FileNotFoundException, ClassNotFoundException, IOException{
		return getStruct(file);
	}
	
	public static boolean apagarTodos() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = getStruct(file);
		usuarios.clear();
		setStruct(usuarios, file);
		return true;
	}
	
	public static boolean editar(String emailAntigo, Usuario novoUsuario) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Usuario> usuarios = getStruct(file);
		Usuario usuarioAntigo = buscarUsuario(emailAntigo);
		if(usuarios.remove(usuarioAntigo)) {
			setStruct(usuarios, file);
			if(adicionarUsuario(novoUsuario)) {
				return true;
			}
		}else {
			adicionarUsuario(usuarioAntigo);
			return false;
		}
		return false;
	}
	
	public static boolean novaExperiencia(String email,int idFilme,int nota) throws FileNotFoundException, ClassNotFoundException, IOException, NotaInvalidaException {
		Usuario u = buscarUsuario(email);
		if(u.novaExperiencia(nota, idFilme)) {
			if(editar(email, u)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean removerExperiencia(String email, int idFilme) throws FileNotFoundException, ClassNotFoundException, IOException {
		Usuario u = buscarUsuario(email);
		if(u.removerExperiencia(idFilme)) {
			if(editar(u.getEmail(), u)) {
				return true;
			}
		}
		return false;
	}
}
