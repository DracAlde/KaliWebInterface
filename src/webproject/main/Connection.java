package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Constants;
import webproject.commun.User;
import webproject.database.DAOFactory;
import webproject.database.UserDao;
import webproject.form.ConnectionForm;

/**
 * Gère la connexion des utilisateurs de l'application
 * @author kilian
 *
 */

public class Connection extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5002932839875106851L;

	private UserDao userDao;

	/**
	 * Initie une connection à la BDD
	 */
    public void init() throws ServletException {
        this.userDao = ((DAOFactory) getServletContext().getAttribute(Constants.CONF_DAO_FACTORY)).getUserDao();
    }
	
    /**
     * Affiche le formulaire de connection
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(Constants.VIEW_CONNECTION).forward(request, response);
	}

	/**
	 * Reçoit les résultat du formulaire de connection. 
	 * S'il est correct, ajoute l'utilisateur à la session
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionForm form = new ConnectionForm(userDao);

		User user = form.connectUser(request);

		HttpSession session = request.getSession();
		
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(Constants.ATT_SESSION_USER, user);
		} else {
			session.setAttribute(Constants.ATT_SESSION_USER, null);
		}

		request.setAttribute(Constants.ATT_FORM, form );
		request.setAttribute(Constants.ATT_USER, user );
		
		if(session.getAttribute(Constants.ATT_SESSION_USER) == null){
			this.getServletContext().getRequestDispatcher(Constants.VIEW_CONNECTION).forward(request, response);
		}else{
			response.sendRedirect( request.getContextPath() + Constants.ADDR_HOME );
		}
	}
}
