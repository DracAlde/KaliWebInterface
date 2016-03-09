package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;

/**
 * Gère une requète GET vers une page de déconnexion en invalidant la session actuelle et en
 * redirigeant l'utilisateur vers la page d'accueil
 * @author kilian
 *
 */

public class Disconnection extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8626332264221002661L;

	/**
	 * Déconnecte une session et redirige vers la page d'accueil
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + Constants.ADDR_INDEX);
    }
	
}
