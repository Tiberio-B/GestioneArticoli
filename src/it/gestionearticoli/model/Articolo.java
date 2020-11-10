package it.gestionearticoli.model;

public class Articolo extends Record {

	private String codice;
	private String descrizione;
	private Integer prezzo;
	private Categoria categoria;
	
	public Articolo() {}
	
	public Articolo(String codice, String descrizione, Integer prezzo, Categoria categoria) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.categoria = categoria;
	}

	public Articolo(Categoria categoria) {
		this.categoria = categoria;
	}

	public Articolo(String codice, String descrizione, Integer prezzo) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
