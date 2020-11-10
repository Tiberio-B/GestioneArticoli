package it.gestionearticoli.web.servlet.categoria;

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

@WebServlet("/PrepareUpdateCategoriaServlet")
public class PrepareUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareUpdateCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if ( utente == null) {
			String errorMessage = "Devi essere loggato per effettuare questa operazione.";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		if ( !(utente.isAdmin() || utente.isOperator())) {
			String errorMessage = "Non possiedi le credenziali necessarie ad effettuare questa operazione.\n"+
					"Ruolo richiesto: Admin o Operator, Ruolo attuale: "+utente.getRuolo().name();
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		String idParam = request.getParameter("idCat");
		Long idOld = !idParam.isEmpty() ? Long.parseLong(idParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (idOld < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// ottiene la categoria da idOld
		Categoria categoriaOld = null;
		try {
			categoriaOld = ServiceFactory.getCategoriaServiceInstance().trova(idOld);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("categoriaOld", categoriaOld);

		request.getRequestDispatcher("jsp/categoria/update-categoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
