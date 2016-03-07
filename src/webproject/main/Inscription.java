package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;
import webproject.commun.User;
import webproject.database.DAOFactory;
import webproject.database.UserDao;
import webproject.form.InscriptionForm;

/**
 * 
 * @author kilian
 *
 */

public class Inscription extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9208699097421985768L;

	private UserDao userDao;

    public void init() throws ServletException {
    	System.out.println("Inscription initialized");
        this.userDao = ((DAOFactory) getServletContext().getAttribute(Constants.CONF_DAO_FACTORY)).getUserDao();
    }
	
	/**
	 * Affiche inscription.jsp
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(Constants.VIEW_INSCRIPTION).forward(request, response );
	}

	/**
	 * Reçoit les informatons du formulaire inscription.jsp (username, password, confirmation, email)
	 * créer un utilisateur
	 * Affiche inscription.jsp avec un message d'erreur ou de succes
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		InscriptionForm form = new InscriptionForm(userDao);
		User user = form.createUser(request);
		
		request.setAttribute(Constants.ATT_FORM, form);
		request.setAttribute(Constants.ATT_USER, user);

		this.getServletContext().getRequestDispatcher(Constants.VIEW_INSCRIPTION).forward(request, response );
	}
}
