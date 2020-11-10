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

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertArticoloServlet() {
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
		
		// validiamo input
		String idCatParam = request.getParameter("idCat");
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		Long idCat = !idCatParam.isEmpty() ? Long.parseLong(idCatParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione, sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/articolo/insert-articolo.jsp").forward(request, response);
			return;
		}
		
		Categoria categoria = null;

		//occupiamoci delle operazioni di business
		try {
			categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
			Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);
			ServiceFactory.getArticoloServiceInstance().inserisci(articoloInstance);
			request.setAttribute("listaArticoliAttribute", ServiceFactory.getArticoloServiceInstance().elenca());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);

	}

}
