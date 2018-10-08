package com.ifpb.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoListGenerico<T> {
	public static <T> List<T> getStruct(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		if(file.length()>0) {
			try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))){
				return (ArrayList<T>) input.readObject();
			}
		}
		return new ArrayList<>();
	}
	
	public static <T> void setStruct(T objeto, File file) throws FileNotFoundException, IOException {
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))){
			output.writeObject(objeto);
		}
	}
}
