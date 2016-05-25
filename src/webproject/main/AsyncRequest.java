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

/**
 * Class used to store command responses (AsyncItem)
 * and to send the result when it is called the client
 * @author kilian
 *
 */
public class AsyncRequest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LinkedList<AsyncItem> responsesList = new LinkedList<AsyncItem>();

	/**
	 * Create a txt response with the value corresponding to the sessionID
	 * and the tool
	 */
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

	/**
	 * Select the response in responsesList corresponding with the
	 * sessionID and the tool
	 * @param sessionID
	 * @param tool
	 * @return
	 */
	public static String getResponse(String sessionID, String tool){
		String res = null;

		for(AsyncItem item : responsesList){
			if(item.getSessionID().equals(sessionID) && item.getTool().equals(tool)){
				res = item.getResponse();
			}
		}

		return res;
	}
}
