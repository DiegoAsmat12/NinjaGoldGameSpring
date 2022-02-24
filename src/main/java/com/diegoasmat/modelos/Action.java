package com.diegoasmat.modelos;

public class Action {
	private String message;
	private int valor;
	
	public Action(String message, int valor) {
		this.message=message;
		this.valor=valor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
