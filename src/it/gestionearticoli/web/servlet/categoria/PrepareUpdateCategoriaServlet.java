package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/PrepareUpdateCategoriaServlet")
public class PrepareUpdateCategoriaServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public PrepareUpdateCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// verifica ruolo utente
		Utente.Ruolo[] ruoliRichiesti = {Utente.Ruolo.Admin, Utente.Ruolo.Operator};
		int auth = validateUser(request, "utente", ruoliRichiesti);
		if (auth <= 0) {
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		Long idOld = validateID(request, "idCat");
		if (idOld < 0) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// ottiene la categoria da idOld
		Categoria categoriaOld = null;
		try {
			categoriaOld = ServiceFactory.getCategoriaServiceInstance().trova(idOld);
			request.setAttribute("categoriaOld", categoriaOld);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("jsp/categoria/update-categoria.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
