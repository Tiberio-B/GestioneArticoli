package it.gestionearticoli.service.articolo;

import java.util.List;

import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.model.Articolo;

public interface ArticoloService {
	
	public ArticoloDAO getDao() ;
	
	public void setDao(ArticoloDAO tDao);

	public List<Articolo> elenca() throws Exception;

	public Articolo trova(Long id) throws Exception;

	public List<Articolo> trovaDa(Articolo input) throws Exception;
	
	public int aggiorna(Articolo input) throws Exception;

	public int inserisci(Articolo input) throws Exception;

	public int rimuovi(Articolo input) throws Exception;
	
	public List<Articolo> cerca(Articolo input) throws Exception;
	
}