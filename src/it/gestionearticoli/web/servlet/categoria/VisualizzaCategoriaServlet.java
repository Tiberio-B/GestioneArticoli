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

@WebServlet("/VisualizzaCategoriaServlet")
public class VisualizzaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualizzaCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// validazione input
		String idCatParam = request.getParameter("idCat");
		Long idCat = !idCatParam.isEmpty() ? Long.parseLong(idCatParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		// ottiene la categoria specificata da idCat
		Categoria categoria = null;
		try {
			categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		request.setAttribute("categoria", categoria);
		
		// genera un articolo-esempio per la categoria
		Articolo articolo = new Articolo();
		articolo.setCategoria(categoria);
		// ottiene tutti gli articoli della categoria
		List<Articolo> articoli = null;
		try {
			articoli = ServiceFactory.getArticoloServiceInstance().trovaDa(articolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaArticoliAttribute", articoli);
		request.setAttribute("filtered", true);
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
