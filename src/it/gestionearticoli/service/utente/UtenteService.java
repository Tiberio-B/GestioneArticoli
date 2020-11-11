package it.gestionearticoli.service.utente;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.AbstractGenericService;

public abstract class UtenteService extends AbstractGenericService<Utente> {
	
	public abstract Utente auth(String username, String password) throws Exception;
	
//	public UtenteDAO getDao();
//	
//	public void setDao(UtenteDAO tDao);
//
//	public List<Utente> elenca() throws Exception;
//
//	public Utente trova(Long id) throws Exception;
//
//	public List<Utente> trovaDa(Utente input) throws Exception;
//	
//	public int aggiorna(Utente input) throws Exception;
//
//	public int inserisci(Utente input) throws Exception;
//
//	public int rimuovi(Utente input) throws Exception;
//	
//	public List<Utente> cerca(Utente input) throws Exception;
	
}
