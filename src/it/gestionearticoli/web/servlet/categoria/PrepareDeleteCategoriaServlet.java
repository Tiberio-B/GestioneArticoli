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

@WebServlet("/PrepareDeleteCategoriaServlet")
public class PrepareDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !(utente.isAdmin())) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		String idParam = request.getParameter("idCat");
		Long idCat = !idParam.isEmpty() ? Long.parseLong(idParam) : 0;
				
		// se la validazione fallisce torno in pagina
		if (idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// ottiene la categoria da idCat
		Categoria categoria = null;
		try {
			categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("categoriaAttr", categoria);

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/delete-categoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
