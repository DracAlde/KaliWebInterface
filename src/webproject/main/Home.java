package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Constants;

public class Home extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607794654897494588L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		if(session.getAttribute(Constants.ATT_SESSION_USER) == null){
			response.sendRedirect( request.getContextPath() + Constants.ADDR_INDEX );
		}else{
			this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
		}
	}
}
