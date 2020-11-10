package it.gestionearticoli.model;

public class Categoria extends Record {
	
	private String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Categoria() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
