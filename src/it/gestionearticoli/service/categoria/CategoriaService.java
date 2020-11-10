package it.gestionearticoli.service.categoria;

import java.util.List;

import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;

public interface CategoriaService {
	
	public CategoriaDAO getDao();
	
	public void setDao(CategoriaDAO tDao);

	public List<Categoria> elenca() throws Exception;

	public Categoria trova(Long id) throws Exception;

	public List<Categoria> trovaDa(Categoria input) throws Exception;
	
	public int aggiorna(Categoria input) throws Exception;

	public int inserisci(Categoria input) throws Exception;

	public int rimuovi(Categoria input) throws Exception;
	
	public List<Categoria> cerca(Categoria input) throws Exception;
}