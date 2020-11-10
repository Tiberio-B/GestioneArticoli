package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Utente;

@WebServlet("/PrepareInsertCategoriaServlet")
public class PrepareInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");	
		if ( utente == null || !(utente.isAdmin() || utente.isOperator())) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("jsp/categoria/insert-categoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
