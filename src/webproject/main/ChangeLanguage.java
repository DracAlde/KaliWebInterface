package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Constants;
import webproject.commun.Language;
import webproject.commun.Tools;

public class ChangeLanguage extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);
		
		HttpSession session = request.getSession();
		Language language = (Language) session.getAttribute(Constants.SESS_LANG);
		language.switchLanguage();
		session.setAttribute("language", language);
		
		System.out.println(Tools.detectLocale(request));
		this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
	}
}
