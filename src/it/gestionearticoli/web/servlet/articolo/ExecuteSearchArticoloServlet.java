package it.gestionearticoli.web.servlet.articolo;

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

@WebServlet("/ExecuteSearchArticoloServlet")
public class ExecuteSearchArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteSearchArticoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// validazione input
		String idCatParam = request.getParameter("idCat");
		String codice = request.getParameter("codice");
		String descrizione = request.getParameter("descrizione");
		String prezzoParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoParam.isEmpty() ? Integer.parseInt(prezzoParam) : -1;
		Long idCat = !idCatParam.isEmpty() ? Long.parseLong(idCatParam) : -1;
				
		Articolo articolo = new Articolo(codice, descrizione, prezzo);
		
		// se specificata, setta la categoria
		if (!(idCat < 0)) {
			Categoria categoria = null;
			try {
				categoria = ServiceFactory.getCategoriaServiceInstance().trova(idCat);
				articolo.setCategoria(categoria);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		List<Articolo> articoli = null;
		
		try {
			articoli = ServiceFactory.getArticoloServiceInstance().cerca(articolo);
			request.setAttribute("listaArticoliAttribute", articoli);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("searched", true);
		request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
	}

}
