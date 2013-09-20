/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haegerp.jsf.filter;

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

/**
 * Diese Klasse ist ein Filter und der Benutzer wird zur Loginseite führt, wenn
 * er nicht angemeldet hat
 *
 * @author Fabio Codinha
 */
public class LoginPageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession(true);

        Object obj = session.getAttribute("idemployee");

        if (!(request.getServletPath().contains("javax.faces.resource")
                || request.getServletPath().equals("/login.jsf"))
                && obj == null) {
            response.sendRedirect("/HaegERPJSF/login.jsf");
        } else {
            if (request.getServletPath().contains("/article/")
                    && session.getAttribute("pArticle") == (Integer)0) {
                response.sendError(403);
            }
            
            if (request.getServletPath().contains("/bpartner/")
                    && session.getAttribute("pBusinessPart") == (Integer)0) {
                response.sendError(403);
            }
            
            if (request.getServletPath().contains("/hresources/")
                    && session.getAttribute("pHumanResources") == (Integer)0) {
                response.sendError(403);
            }
            
            if (request.getServletPath().contains("/company")
                    && session.getAttribute("pCompany") == (Integer)0) {
                response.sendError(403);
            }
            
            if (request.getServletPath().contains("/sorder/")
                    && session.getAttribute("pSupplierOrders") == (Integer)0) {
                response.sendError(403);
            }
            
            if (request.getServletPath().contains("/coffer/")
                    && session.getAttribute("pClientOffers") == (Integer)0) {
                response.sendError(403);
            }
            
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
