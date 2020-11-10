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

@WebServlet("/ExecuteInsertCategoriaServlet")
public class ExecuteInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertCategoriaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if ( utente == null || utente.isGuest()) {	
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		// validiamo input
		String nome = request.getParameter("nome");
		// se la validazione fallisce torno in pagina
		if (nome.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/insert-categoria.jsp").forward(request, response);
			return;
		}

		//occupiamoci delle operazioni di business
		Categoria categoria = new Categoria(nome);
		try {
			ServiceFactory.getCategoriaServiceInstance().inserisci(categoria);
			request.setAttribute("listaCategorie", ServiceFactory.getCategoriaServiceInstance().elenca());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/categorie.jsp").forward(request, response);
	}

}
