package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/ConfirmUpdateArticoloServlet")
public class ConfirmUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmUpdateArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");	
		if ( utente == null || utente.isGuest()) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		String idOldParam = request.getParameter("idOld");	
		Long idOld = !idOldParam.isEmpty() ? Long.parseLong(idOldParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (idOld < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/articolo/update-articolo.jsp").forward(request, response);
			return;
		}
		
		// conserva l'articolo prima della modifica come articoloOld
		try {
			Articolo articoloOld = ServiceFactory.getArticoloServiceInstance().trova(idOld);
			request.setAttribute("articoloOld", articoloOld);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ottieni le informazioni aggiornate dall'input utente
		String idCatParam = request.getParameter("idCat");
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		Long idCat = !idCatParam.isEmpty() ? Long.parseLong(idCatParam) : 0;
		
		// ottieni la categorie dall'input utente
		Categoria categoria = null;
		try {
			categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Articolo articoloNew = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);
		articoloNew.setId(idOld);
		request.setAttribute("articoloNew", articoloNew);
				
		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || idCat < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/articolo/update-articolo.jsp").forward(request, response);
			return;
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/articolo/confirm-update-articolo.jsp").forward(request, response);
	}
}
