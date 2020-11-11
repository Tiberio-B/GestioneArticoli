package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/ConfirmUpdateArticoloServlet")
public class ConfirmUpdateArticoloServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmUpdateArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// verifica ruolo utente, se fallisce reindirizza
		Utente.Ruolo[] ruoliAutorizzati = {Utente.Ruolo.Admin, Utente.Ruolo.Operator};
		int auth = verifyUser(request, "utente", ruoliAutorizzati);
		if (auth <= 0) {
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione campo ID, se fallisce reindirizza
		Long idOld = validateID(request, "idOld");
		if (idOld < 0) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// conserva l'articolo come articoloOld prima della modifica
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
		
		Articolo articoloNew = new Articolo(idOld, codiceInputParam, descrizioneInputParam, prezzo, categoria);
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
