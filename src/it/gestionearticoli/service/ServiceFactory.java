package it.gestionearticoli.service;

import it.gestionearticoli.dao.articolo.*;
import it.gestionearticoli.dao.categoria.*;
import it.gestionearticoli.dao.utente.*;
import it.gestionearticoli.service.articolo.*;
import it.gestionearticoli.service.categoria.*;
import it.gestionearticoli.service.utente.*;

public class ServiceFactory {

	// implementiamo il singleton in modo da evitare
	// proliferazione di riferimenti
	private static ArticoloService ARTICOLOSERVICE_INSTANCE = null;
	private static ArticoloDAO ARTICOLODAO_INSTANCE = null;
	
	private static CategoriaService CATEGORIASERVICE_INSTANCE = null;
	private static CategoriaDAO CATEGORIADAO_INSTANCE = null;
	
	private static UtenteService UTENTESERVICE_INSTANCE = null;
	private static UtenteDAO UTENTEDAO_INSTANCE = null;

	public static ArticoloService getArticoloServiceInstance() {
		if (ARTICOLOSERVICE_INSTANCE == null)
			ARTICOLOSERVICE_INSTANCE = new ArticoloServiceImpl();

		if (ARTICOLODAO_INSTANCE == null)
			ARTICOLODAO_INSTANCE = new ArticoloDAOImpl();

		ARTICOLOSERVICE_INSTANCE.setDao(ARTICOLODAO_INSTANCE);

		return ARTICOLOSERVICE_INSTANCE;
	}
	
	public static CategoriaService getCategoriaServiceInstance() {
		if (CATEGORIASERVICE_INSTANCE == null)
			CATEGORIASERVICE_INSTANCE = new CategoriaServiceImpl();

		if (CATEGORIADAO_INSTANCE == null)
			CATEGORIADAO_INSTANCE = new CategoriaDAOImpl();

		CATEGORIASERVICE_INSTANCE.setDao(CATEGORIADAO_INSTANCE);

		return CATEGORIASERVICE_INSTANCE;
	}
	
	public static UtenteService getUtenteServiceInstance() {
		if (UTENTESERVICE_INSTANCE == null)
			UTENTESERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTEDAO_INSTANCE == null)
			UTENTEDAO_INSTANCE = new UtenteDAOImpl();

		UTENTESERVICE_INSTANCE.setDao(UTENTEDAO_INSTANCE);

		return UTENTESERVICE_INSTANCE;
	}

}
