package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;
import webproject.form.TlsSledForm;

public class TlsSled extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(Constants.VIEW_TLS_SLED).forward(request, response);
	}
	


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		TlsSledForm form = new TlsSledForm();
		form.validateTlsSled(request);
		request.setAttribute(Constants.ATT_FORM,form);
		this.getServletContext().getRequestDispatcher(Constants.VIEW_TLS_SLED).forward(request, response);
	}
}
