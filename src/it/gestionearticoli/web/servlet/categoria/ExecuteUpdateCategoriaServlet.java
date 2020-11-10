package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;
import it.gestionearticoli.service.categoria.CategoriaService;

@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || utente.isGuest()) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione input utente
		String idParam = request.getParameter("idCat");
		String nomeCat = request.getParameter("nomeNew");
		Long idCat = !idParam.isEmpty() ? Long.parseLong(idParam) : 0;
		
		// se la validazione fallisce torno in pagina
		if (idCat < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
				
		Categoria categoria = new Categoria();
		categoria.setId(idCat);
		categoria.setNome(nomeCat);
			
		// aggiorna la categoria sul db
		try {
			CategoriaService service =  ServiceFactory.getCategoriaServiceInstance();
			service.aggiorna(categoria);
			request.setAttribute("listaCategorie", service.elenca());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
	}
}
