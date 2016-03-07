package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;

public class Main extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339634529195830575L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(Constants.VIEW_INDEX).forward(request, response);
    }
}
