package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/PrepareInsertCategoriaServlet")
public class PrepareInsertCategoriaServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// verifica ruolo utente
		Utente.Ruolo[] ruoliRichiesti = {Utente.Ruolo.Admin, Utente.Ruolo.Operator};
		int auth = verifyUser(request, "utente", ruoliRichiesti);
		if (auth <= 0) {
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("jsp/categoria/insert-categoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
