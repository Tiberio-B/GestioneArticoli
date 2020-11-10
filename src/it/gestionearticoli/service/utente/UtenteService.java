package it.gestionearticoli.service.utente;

import java.util.List;

import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public interface UtenteService {
	
	public UtenteDAO getDao();
	
	public void setDao(UtenteDAO tDao);

	public List<Utente> elenca() throws Exception;

	public Utente trova(Long id) throws Exception;

	public List<Utente> trovaDa(Utente input) throws Exception;
	
	public int aggiorna(Utente input) throws Exception;

	public int inserisci(Utente input) throws Exception;

	public int rimuovi(Utente input) throws Exception;
	
	public List<Utente> cerca(Utente input) throws Exception;

	public Utente auth(String username, String password) throws Exception;
}
