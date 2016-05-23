package webproject.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.AsyncItem;
import webproject.commun.Constants;
import webproject.commun.History;
import webproject.commun.Tools;

public class AsyncRequest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LinkedList<AsyncItem> responsesList = new LinkedList<AsyncItem>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("text/html");

		String tool = Tools.getFieldValue(request, "tool");
		String res = null;

		HttpSession session = request.getSession();
		if(tool != null){
			res = getResponse(session.getId(), tool);
		}
		
		if(res == null){
			res = "";
		}
		PrintWriter out = response.getWriter();
		out.print(res);
	}

	public static synchronized void addAsyncItem(AsyncItem item){
		responsesList.add(item);
	}

	public static String getResponse(String sessionID, String tool){
		String res = null;

		for(AsyncItem item : responsesList){
			if(item.getSessionID().equals(sessionID) && item.getTool().equals(tool)){
				res = item.getResponse();
			}
		}

		return res;
	}

	public static void read(HttpServletRequest request, String tool){
		History history = (History) request.getSession().getAttribute(Constants.ATT_SESSION_HISTORY);
		System.out.println(history.getResponse(tool));
	}

}
