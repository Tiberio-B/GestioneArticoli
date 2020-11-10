package it.gestionearticoli.service;

import java.util.List;

import it.gestionearticoli.dao.IBaseDAO;

public interface GenericService<T> {

	public IBaseDAO<T> getDao();
	
	public void setDao(IBaseDAO<T> tDao);

	public List<T> elenca() throws Exception;

	public T trova(Long id) throws Exception;
	
	public List<T> trovaDa(T input) throws Exception;

	public int aggiorna(T input) throws Exception;

	public int inserisci(T input) throws Exception;

	public int rimuovi(T input) throws Exception;
	
	public List<T> cerca(T input) throws Exception;
}
