package it.gestionearticoli.model;

public class Utente extends Identified {

	private String nome;
	private String cognome;
	private String cf;
	private String username;
	private String password;
	private Utente.Ruolo ruolo;
	
	{
		this.setId(0L);
		this.nome = "";
		this.cognome = "";
		this.cf = "";
		this.username = "";
		this.password = "";
		this.ruolo = Ruolo.Guest;
	}
	
	public Utente() {}
	
	public Utente(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
	public boolean isAdmin() {
		return (ruolo == Ruolo.Admin);
	}
	
	public boolean isOperator() {
		return (ruolo == Ruolo.Operator);
	}
	
	public boolean isGuest() {
		return (ruolo == Ruolo.Guest);
	}
	
	@Override
	public String toString() {
		return nome + " " + cognome + ", " + cf;
	}

	public static enum Ruolo {
		Admin,
		Operator,
		Guest
	}
}