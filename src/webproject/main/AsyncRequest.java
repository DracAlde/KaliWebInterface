package webproject.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Constants;
import webproject.commun.History;

public class AsyncRequest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("text/html");

		String tool = (String) request.getAttribute("tool");

		HttpSession session = request.getSession();
		History history = (History) session.getAttribute(Constants.ATT_SESSION_HISTORY);

		if( history == null){
			System.out.println("Request received - history creation");
			history = new History();
			session.setAttribute(Constants.ATT_SESSION_HISTORY, history);
			PrintWriter out = response.getWriter();
			out.println("");
		}else{
			// Actual logic goes here.
			PrintWriter out = response.getWriter();
			if(history.getResponse(tool) == null){
				System.out.println("Request received - nothing to send");
				out.println("");
			}else{
				System.out.println("Request received - send response");
				out.println(history.getResponse(tool));
			}
		}
	}
	
	public static void read(HttpServletRequest request, String tool){
		History history = (History) request.getSession().getAttribute(Constants.ATT_SESSION_HISTORY);
		System.out.println(history.getResponse(tool));
	}

}
