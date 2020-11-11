package it.gestionearticoli.model;

public class Categoria extends Identified {
	
	private String nome;

	public Categoria() {}

	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Categoria(String nome) {
		this(null, nome);
	}

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
