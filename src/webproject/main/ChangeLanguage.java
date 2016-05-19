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
		
		if(language == null){
			session.setAttribute("language", new Language(request));
			this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
		}
		
		language.switchLanguage();
		session.setAttribute("language", language);
		
		if(null == language.getCurrentToolLanguage()){
			this.getServletContext().getRequestDispatcher(Constants.VIEW_HOME).forward(request, response);
		}else{
			switch(language.getCurrentToolLanguage()){
			case Constants.TOOL_TLS_SLED:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_TLS_SLED);
				break;
			case Constants.TOOL_DNS_RECON:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_DNS_RECON);
				break;
			case Constants.TOOL_HYDRA:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_HYDRA);
				break;
			case Constants.TOOL_NIKTO:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_NIKTO);
				break;
			case Constants.TOOL_THE_HARVESTER:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_THEHARVESTER);
				break;
			case Constants.TOOL_ZENMAP:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_ZENMAP);
				break;
			default:
				response.sendRedirect(Constants.URL_REDIRECT + Constants.ADDR_HOME);
			}
		}
	}
}
