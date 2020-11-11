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

@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateArticoloServlet() {
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
		
		// validazione parametri Stringa, se fallisce reindirizza
		String codiceParam = validateStringParam(request, "codice");
		String descrizioneParam = validateStringParam(request, "descrizione");
		if (codiceParam == null || descrizioneParam == null) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		Integer prezzo = validatePrezzo(request, "prezzo");
				
		// validazione campo ID, se fallisce reindirizza
		Long idCat = validateID(request, "idCat");
		Long idArt = validateID(request, "idArt");
		if (idCat < 0 || idArt < 0) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		Articolo articolo = new Articolo(idArt, codiceParam, descrizioneParam, prezzo);
		Categoria categoria = null;
		
		// ottiene la categoria specificata da idCat e la setta per l'articolo
		try {
			categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
			articolo.setCategoria(categoria);
			ServiceFactory.getArticoloServiceInstance().aggiorna(articolo);
			
			request.setAttribute("listaArticoliAttribute", ServiceFactory.getArticoloServiceInstance().elenca());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
	}
}
