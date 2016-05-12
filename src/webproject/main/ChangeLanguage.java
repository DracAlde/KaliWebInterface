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

		if(language.getCurrentToolLanguage() == Constants.TOOL_TLS_SLED){

		}
		switch(language.getCurrentToolLanguage()){
		case Constants.TOOL_TLS_SLED:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_TLS_SLED).forward(request, response);
			break;
		case Constants.TOOL_DNS_RECON:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_DNS_RECON).forward(request, response);
			break;
		case Constants.TOOL_HYDRA:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_HYDRA).forward(request, response);
			break;
		case Constants.TOOL_NIKTO:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_NIKTO).forward(request, response);
			break;
		case Constants.TOOL_THE_HARVESTER:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_THEHARVESTER).forward(request, response);
			break;
		case Constants.TOOL_ZENMAP:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_ZENMAP).forward(request, response);
			break;
		default:
			this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
		}
	}
}
