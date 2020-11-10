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

@WebServlet("/DeleteCategoriaServlet")
public class DeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !(utente.isAdmin())) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input
		String idParam = request.getParameter("idCat");
		Long idCat = !idParam.isEmpty() ? Long.parseLong(idParam) : 0;
				
		// se la validazione fallisce torno in pagina
		if (idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
		
		Categoria categoria = new Categoria();
		categoria.setId(idCat);
		
		try {
			CategoriaService service = ServiceFactory.getCategoriaServiceInstance();
			service.rimuovi(categoria);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.setAttribute("listaCategorie", service.elenca());
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
