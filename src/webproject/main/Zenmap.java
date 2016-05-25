package webproject.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.History;
import webproject.commun.Language;
import webproject.commun.Tools;
import webproject.form.ZenmapForm;

/**
 * Servlet for the Zenmap tool
 * @author arn0f
 *
 */
public class Zenmap extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Respond with the zenmap view
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);

		HttpSession session = request.getSession();
		Language language = (Language) session.getAttribute(Constants.SESS_LANG);
		if(language == null){
			language = new Language(request);
		}
		language.setCurrentTool(Constants.TOOL_ZENMAP);
		session.setAttribute(Constants.SESS_LANG, language);


		//System.out.println(Tools.detectLocale(request));
		this.getServletContext().getRequestDispatcher(Constants.VIEW_ZENMAP).forward(request, response);
	}
	
	
	/**
	 * Use tls zenmap form to create a command
	 * add the command in the session history
	 * send tls zenmap view
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);

		ZenmapForm form = new ZenmapForm();

		Command command = form.validateZenmap(request);

		HttpSession session = request.getSession();
		History history = (History) session.getAttribute(Constants.ATT_SESSION_HISTORY);

		if( history == null){
			history = new History();
		}

		history.addCommand(command);
		session.setAttribute(Constants.ATT_SESSION_HISTORY, history);

		request.setAttribute(Constants.ATT_FORM,form);
		this.getServletContext().getRequestDispatcher(Constants.VIEW_ZENMAP).forward(request, response);
	}
}
