package it.gestionearticoli.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// se l'utente è già loggato, torna in homepage
		if(request.getSession().getAttribute("utente") != null ) {
			request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// se la validazione fallisce torno in pagina
		if (username.isEmpty() || password.isEmpty()) {
			request.setAttribute("errorMessage", "Inserire username e password per effettuare l'accesso");
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		
		Utente utente = null;
		try {
			utente = ServiceFactory.getUtenteServiceInstance().auth(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (utente == null) {
			request.setAttribute("errorMessage", "L'username o la password sono errati");
			request.getRequestDispatcher("jsp/utente/login.jsp").forward(request, response);
			return;
		}
		HttpSession sess = request.getSession();
		sess.setAttribute("utente", utente);
		sess.setAttribute("cannotInsert", (utente.isGuest()));
		sess.setAttribute("cannotUpdate", (utente.isGuest()));
		sess.setAttribute("cannotDelete", !(utente.isAdmin()));
		
		request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
	}
}
