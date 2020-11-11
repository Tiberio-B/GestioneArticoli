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
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/PrepareDeleteCategoriaServlet")
public class PrepareDeleteCategoriaServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// verifica ruolo utente
		Utente.Ruolo[] ruoliRichiesti = {Utente.Ruolo.Admin};
		int auth = validateUser(request, "utente", ruoliRichiesti);
		if (auth <= 0) {
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		Long idCat = validateID(request, "idCat");
		if (idCat < 0) {
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
