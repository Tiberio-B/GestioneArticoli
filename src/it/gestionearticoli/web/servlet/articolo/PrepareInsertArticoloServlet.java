package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/PrepareInsertArticoloServlet")
public class PrepareInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertArticoloServlet() {
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
		
		List<Categoria> categorie = null;
		try {
			categorie = ServiceFactory.getCategoriaServiceInstance().elenca();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaCategorie", categorie);

		request.getRequestDispatcher("jsp/articolo/insert-articolo.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}