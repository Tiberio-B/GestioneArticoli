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
import it.gestionearticoli.web.servlet.MyAbstractServlet;

@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends MyAbstractServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// verifica ruolo utente, se fallisce reindirizza
		Utente.Ruolo[] ruoliAutorizzati = {Utente.Ruolo.Admin, Utente.Ruolo.Operator};
		int auth = verifyUser(request, "utente", ruoliAutorizzati);
		if (auth <= 0) {
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validazione parametro Stringa, se fallisce reindirizza
		String nomeCat = validateStringParam(request, "nomeNew");
		if (nomeCat == null) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
				
		// validazione campo ID, se fallisce reindirizza
		Long idCat = validateID(request, "idCat");
		if (idCat < 0) {
			request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
			return;
		}
				
		Categoria categoria = new Categoria(idCat, nomeCat);
			
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
