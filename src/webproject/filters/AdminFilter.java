package webproject.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.commun.Constants;
import webproject.commun.Level;
import webproject.commun.User;

public class AdminFilter implements Filter {
	
    public void init(FilterConfig config) throws ServletException {
    }
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute(Constants.ATT_SESSION_USER);

        if(user == null) {
            response.sendRedirect(request.getContextPath() + Constants.ADDR_CONNECTION);
        } else {
        	if(user.getLevel().equals(Level.ADMIN)){
        		chain.doFilter(request, response);
        	}else{
        		response.sendRedirect(request.getContextPath() + Constants.ADDR_HOME);
        	}
        }
    }

    public void destroy() {
    }
}
