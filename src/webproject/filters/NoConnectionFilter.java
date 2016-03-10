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

public class NoConnectionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Vérifie qu'un utilisateur est conncté ou pas.
     * S'il n'est pas connecté, l'utilisateur accède à la ressource demandée
     * sinon, affiche la page home
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute(Constants.ATT_SESSION_USER) == null) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + Constants.ADDR_HOME);
        }
    }

    public void destroy() {
    }

}
