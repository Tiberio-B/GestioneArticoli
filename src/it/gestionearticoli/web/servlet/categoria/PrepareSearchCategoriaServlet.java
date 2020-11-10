package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/PrepareSearchCategoriaServlet")
public class PrepareSearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareSearchCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Categoria> categorie = null;
		try {
			categorie = ServiceFactory.getCategoriaServiceInstance().elenca();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaCategorie", categorie);

		request.getRequestDispatcher("jsp/categoria/search-categoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}