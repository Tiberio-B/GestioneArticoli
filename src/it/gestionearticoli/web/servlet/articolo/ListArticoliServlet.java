package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/ListArticoliServlet")
public class ListArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListArticoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//preparo la lista di articoli
		List<Articolo> articoli = null;
		try {
			articoli = ServiceFactory.getArticoloServiceInstance().elenca();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaArticoliAttribute", articoli);
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
