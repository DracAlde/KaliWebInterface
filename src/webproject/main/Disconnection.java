package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;

public class Disconnection extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8626332264221002661L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		response.sendRedirect(Constants.ADDR_INDEX);
    }
	
}
