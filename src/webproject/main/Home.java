package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;

/**
 * Page d'accueil d'une session connectée
 * @author kilian
 *
 */

public class Home extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607794654897494588L;

	/**
	 * Affiche la page d'accueil
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
	}
}
