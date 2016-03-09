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

/**
 * Filtre qui vérifie qu'un utilisateur est connecté avant d'accèder à une ressoure.
 * Redirige vers la page de connexion si l'user n'est pas connecté.
 * @author kilian
 *
 */

public class ConnectionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Vérifie qu'un utilisateur est conncté ou pas.
     * S'il est connecté, l'utilisateur accède à la ressource demandée
     * sinon, affiche le formulaire de connection
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute(Constants.ATT_SESSION_USER) == null) {
            response.sendRedirect(request.getContextPath() + Constants.ADDR_CONNECTION);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
