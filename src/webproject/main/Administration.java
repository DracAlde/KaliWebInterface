package webproject.main;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.commun.Constants;
import webproject.database.DAOException;
import webproject.database.DAOFactory;
import webproject.database.UserDao;

public class Administration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8720649088136462695L;

	private UserDao userDao;

	public void init() throws ServletException {
		this.userDao = ((DAOFactory) getServletContext().getAttribute(Constants.CONF_DAO_FACTORY)).getUserDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			request.setAttribute("list", userDao.getAllUsers());
		}catch(DAOException e){
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher(Constants.VIEW_ADMIN).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Enumeration<String> list = request.getParameterNames();
		String username = "";
		int i = 0;

		while(list.hasMoreElements()){
			username = list.nextElement();
			try {
				userDao.removeUser(username);
				i++;
			}catch(DAOException e){
				e.printStackTrace();
			}
		}

		try{
			request.setAttribute("list", userDao.getAllUsers());
		}catch(DAOException e){
			e.printStackTrace();
		}
		
		request.setAttribute("nbSuccess", i+" compte(s) supprimé(s)");
		this.getServletContext().getRequestDispatcher(Constants.VIEW_ADMIN).forward(request, response);
	}
}