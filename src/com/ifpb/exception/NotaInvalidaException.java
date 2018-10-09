package com.ifpb.exception;

public class NotaInvalidaException extends Exception{
	public NotaInvalidaException() {
		super("A nota informada é inválida\n");
	}
}
