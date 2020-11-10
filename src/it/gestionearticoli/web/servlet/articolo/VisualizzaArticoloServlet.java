package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/VisualizzaArticoloServlet")
public class VisualizzaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualizzaArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// validazione input
		String idParam = request.getParameter("idParam");
		Long idArt = !idParam.isEmpty() ? Long.parseLong(idParam) : 0;
				
		// se la validazione fallisce torno in pagina
		if (idArt < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/articolo/articoli.jsp").forward(request, response);
			return;
		}
		
		// ottiene l'articolo da idArt
		Articolo articolo = null;
		try {
			articolo = ServiceFactory.getArticoloServiceInstance().trova(idArt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("articoloAttr", articolo);

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/articolo/show-articolo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
