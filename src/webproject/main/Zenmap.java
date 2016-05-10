package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;
import webproject.commun.Tools;

public class Zenmap extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);
		this.getServletContext().getRequestDispatcher(Constants.VIEW_ZENMAP).forward(request, response);
	}
}