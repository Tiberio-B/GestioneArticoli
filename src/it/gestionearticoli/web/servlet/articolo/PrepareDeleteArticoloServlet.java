package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

@WebServlet("/PrepareDeleteArticoloServlet")
public class PrepareDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !(utente.isAdmin())) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		long id = Long.valueOf(request.getParameter("idParam"));
		Articolo articolo = null;
		try {
			articolo = ServiceFactory.getArticoloServiceInstance().trova(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("articoloAttr", articolo);

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/articolo/delete-articolo.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
