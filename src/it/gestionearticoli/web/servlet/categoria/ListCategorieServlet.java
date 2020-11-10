package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/ListCategorieServlet")
public class ListCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListCategorieServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaCategorie", ServiceFactory.getCategoriaServiceInstance().elenca());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
