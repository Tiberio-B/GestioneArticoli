package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/ExecuteSearchCategoriaServlet")
public class ExecuteSearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteSearchCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// validazione input
		String nome = request.getParameter("nome");
		
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		List<Categoria> categorie = null;
		try {
			categorie = ServiceFactory.getCategoriaServiceInstance().cerca(categoria);
			request.setAttribute("listaCategorie", categorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
	}

}
