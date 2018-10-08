package com.ifpb.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.dao.DaoListGenerico;
import com.ifpb.model.Usuario;

public class GerenciaUsuario extends DaoListGenerico<Usuario>{
	public static File file = new File("Usuarios");
	
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
		if(buscarUsuario(novoUsuario.getEmail())==null) {
			usuarios.add(novoUsuario);
			GerenciaUsuario.setStruct(usuarios, file);
			return true;
		}
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
}
