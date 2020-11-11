package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/ConfirmUpdateCategoriaServlet")
public class ConfirmUpdateCategoriaServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmUpdateCategoriaServlet() {
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
		
		// validazione parametro Stringa, se fallisce reindirizza
		String nome = validateStringParam(request, "nome");
		if (nome == null) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// conserva la categoria in categoriaOld prima della modifica
		try {
			Categoria categoriaOld = ServiceFactory.getCategoriaServiceInstance().trova(idOld);
			request.setAttribute("categoriaOld", categoriaOld);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("categoriaNew", new Categoria(idOld, nome));

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/confirm-update-categoria.jsp").forward(request, response);
	}
}
