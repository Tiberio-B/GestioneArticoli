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
import it.gestionearticoli.service.articolo.ArticoloService;

@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || utente.isGuest()) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		String idArtParam = request.getParameter("idArt");
		String codiceParam = request.getParameter("codice");
		String descrizioneParam = request.getParameter("descrizione");
		String prezzoParam = request.getParameter("prezzo");
		String idCatParam = request.getParameter("idCat");
		Integer prezzo = !prezzoParam.isEmpty() ? Integer.parseInt(prezzoParam) : 0;
		Long idArt = !idArtParam.isEmpty() ? Long.parseLong(idArtParam) : 0;
		Long idCat = !idCatParam.isEmpty() ? Long.parseLong(idCatParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (codiceParam.isEmpty() || descrizioneParam.isEmpty() || prezzo < 1 || idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}
		
		Articolo articolo = new Articolo(codiceParam, descrizioneParam, prezzo);
		articolo.setId(idArt);
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
