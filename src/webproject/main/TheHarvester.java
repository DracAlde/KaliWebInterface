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
import webproject.form.TheHarvesterForm;

/**
 * Servlet for the The Harvester tool
 * @author arn0f
 *
 */
public class TheHarvester extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Respond with the harvester view
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);

		HttpSession session = request.getSession();
		Language language = (Language) session.getAttribute(Constants.SESS_LANG);
		if(language == null){
			language = new Language(request);
		}
		language.setCurrentTool(Constants.TOOL_THE_HARVESTER);
		session.setAttribute(Constants.SESS_LANG, language);

		this.getServletContext().getRequestDispatcher(Constants.VIEW_THEHARVESTER).forward(request, response);	
	}
	
	/**
	 * Use the harvester form to create a command
	 * add the command in the session history
	 * send the harvester's view
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Tools.initiatePath(request);

		TheHarvesterForm form = new TheHarvesterForm();

		Command command = form.validateTheHarvester(request);

		HttpSession session = request.getSession();
		History history = (History) session.getAttribute(Constants.ATT_SESSION_HISTORY);

		if( history == null){
			history = new History();
		}

		history.addCommand(command);
		session.setAttribute(Constants.ATT_SESSION_HISTORY, history);

		request.setAttribute(Constants.ATT_FORM, form);
		
		this.getServletContext().getRequestDispatcher(Constants.VIEW_THEHARVESTER).forward(request, response);
	}
}
