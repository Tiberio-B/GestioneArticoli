package it.gestionearticoli.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import it.gestionearticoli.model.Utente;

public abstract class MyAbstractServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Long validateID(HttpServletRequest request, String idParamName) {
		Long ret = null;
		try {
			String param = request.getParameter(idParamName);
			ret = Long.parseLong(param);
		} catch (NumberFormatException|NullPointerException e) {
			String errorMessage = "Il campo ID '"+ idParamName +"' dev'essere un intero valido.";
			request.setAttribute("errorMessage", errorMessage);
			return new Long(-1);
		}
		return ret;
	}
	
	public int validateUser(HttpServletRequest request, String utenteParamName, Utente.Ruolo[] ruoliRichiesti) {
		Utente utente = (Utente) request.getSession().getAttribute(utenteParamName);
		if (utente == null) {
			String errorMessage = "Devi essere loggato per effettuare questa operazione.";
			request.setAttribute("errorMessage", errorMessage);
			return -1;
		}
		Utente.Ruolo ruoloUtente = utente.getRuolo();
		String ruoliRichiestiString = "";
		for (Utente.Ruolo ruoloRichiesto : ruoliRichiesti) {
			ruoliRichiestiString += ruoloRichiesto.name();
			if (ruoloUtente == ruoloRichiesto) {
				return 1;
			}
		}
		String errorMessage = "Non possiedi le credenziali necessarie ad effettuare questa operazione.\n"+
							"Ruolo richiesto: " + ruoliRichiestiString + " Ruolo attuale: "+ ruoloUtente.name();
		request.setAttribute("errorMessage", errorMessage);
		return 0;
	}
	
}
