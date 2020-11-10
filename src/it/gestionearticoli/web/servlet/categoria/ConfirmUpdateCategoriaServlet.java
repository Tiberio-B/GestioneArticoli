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

@WebServlet("/ConfirmUpdateCategoriaServlet")
public class ConfirmUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmUpdateCategoriaServlet() {
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
		
		// validazione input
		String nome = request.getParameter("nome");
		String idOldParam = request.getParameter("idOld");	
		Long idOld = !idOldParam.isEmpty() ? Long.parseLong(idOldParam) : 0;
				
		// se la validazione fallisce torno in pagina
		if (nome.isEmpty() || nome == null || idOld < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("jsp/categoria/update-categoria.jsp").forward(request, response);
			return;
		}
		
		// conserva la categoria prima della modifica in categoriaOld
		try {
			CategoriaService service = ServiceFactory.getCategoriaServiceInstance();
			Categoria categoriaOld = service.trova(idOld);
			request.setAttribute("categoriaOld", categoriaOld);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Categoria categoriaNew = new Categoria();
		categoriaNew.setId(idOld);
		categoriaNew.setNome(nome);
		request.setAttribute("categoriaNew", categoriaNew);

		//andiamo ai risultati
		request.getRequestDispatcher("jsp/categoria/confirm-update-categoria.jsp").forward(request, response);
	}
}
