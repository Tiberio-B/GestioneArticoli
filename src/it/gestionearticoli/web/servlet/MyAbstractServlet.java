package it.gestionearticoli.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Utente;

public abstract class MyAbstractServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code stringParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro è una sequenza di caratteri valida
	   * (stringa non vuota e non null), viene ritornata senza modifiche;
	   * altrimenti viene ritornato {@code null} e il parametro {@code errorMessage}
	   * della {@code request} è opportunamente aggiornato con un messaggio di errore.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param stringParamName il nome del parametro da ottenere dalla richiesta
	   * @return String il valore del parametro se è una stringa non vuota e non null;
	   * @return null altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public String validateStringParam(HttpServletRequest request, String stringParamName) {
		String param = request.getParameter(stringParamName);
		if (param == null || param.isEmpty()) { // se stringa invalida, aggiorna messaggio di errore
			String errorMessage = "Il campo '"+ stringParamName +"' deve essere una sequenza di caratteri validi.";
			request.setAttribute("errorMessage", errorMessage);
			return null;
		}
		return param;
	}
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code idParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro è parsabile in un oggetto di tipo {@code Long},
	   * viene ritornato come tale; altrimenti viene ritornato {@code -1}
	   * e il parametro {@code errorMessage} della {@code request} è opportunamente aggiornato
	   * con un messaggio di errore.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param idParamName il nome del parametro da ottenere dalla richiesta
	   * @return Long il valore del parametro se è parsabile in un qualsiasi numero {@code Long}
	   * @return {@code -1} altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public Long validateID(HttpServletRequest request, String idParamName) {
		Long id = null;
		try {
			String param = request.getParameter(idParamName);
			id = Long.parseLong(param);
		} catch (NumberFormatException|NullPointerException e) { // se ID invalido, aggiorna messaggio di errore
			String errorMessage = "Il campo ID '"+ idParamName +"' deve essere un intero valido.";
			request.setAttribute("errorMessage", errorMessage);
			id = new Long(-1);
		}
		return id;
	}
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code utenteParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro corrisponde ad un oggetto di tipo {@code Utente}
	   * il cui valore ruolo {@code Utente.Ruolo} è autorizzato (presente all'interno
	   * dell'array {@code ruoliAutorizzati}), viene ritornato {@code 1};
	   * altrimenti viene ritornato {@code -1} (parametro = {@code null}) oppure
	   * {@code 0} e il parametro {@code errorMessage} della {@code request} è opportunamente
	   * aggiornato con un messaggio di errore che descrive il problema verificatosi,
	   * ed eventualmente i ruoli autorizzati.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param utenteParamName il nome del parametro da ottenere dalla richiesta
	   * @return {@code -1} se il valore del parametro è null
	   * @return {@code 1} se il valore del parametro è un {@code Utente} con campo {@code ruolo} presente in {@code ruoliAutorizzati}
	   * @return {@code 0} altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public int verifyUser(HttpServletRequest request, String utenteParamName, Utente.Ruolo[] ruoliAutorizzati) {
		Utente utente = (Utente) request.getSession().getAttribute(utenteParamName);
		if (utente == null) {
			String errorMessage = "Devi essere loggato per effettuare questa operazione.";
			request.setAttribute("errorMessage", errorMessage);
			return -1; // se null, ritorna -1
		}
		Utente.Ruolo ruoloUtente = utente.getRuolo();
		String ruoliAutorizzatiString = ""; // per la stampa del messaggio di errore
		for (int i=0; i < ruoliAutorizzati.length; i++) { // itera sui ruoli autorizzati
			Utente.Ruolo ruoloAutorizzato = ruoliAutorizzati[i];
			if (ruoloUtente == ruoloAutorizzato) return 1; // se autorizzato, ritorna 1
			ruoliAutorizzatiString += ruoloAutorizzato.name();
			if (i < ruoliAutorizzati.length-1) { // tranne l'ultima iterazione
				ruoliAutorizzatiString += ", ";
			}
		}
		String errorMessage = "Non possiedi le credenziali necessarie ad effettuare questa operazione.\n"+
							"Ruolo richiesto: " + ruoliAutorizzatiString + " Ruolo attuale: "+ ruoloUtente.name();
		request.setAttribute("errorMessage", errorMessage);
		return 0; // se non autorizzato ritorna 0
	}
	
	public Integer validatePrezzo(HttpServletRequest request, String prezzoParamName) {
		String prezzoParam = request.getParameter(prezzoParamName);
		return prezzoParam.isEmpty() ? 0 : Integer.parseInt(prezzoParam);
	}
}
