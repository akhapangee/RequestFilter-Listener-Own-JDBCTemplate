package com.akhilesh.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akhilesh
 */
public abstract class Controller extends HttpServlet {

    protected String viewPath = "/WEB-INF/views/";
    
    protected void view(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(viewPath + page + ".jsp").forward(request, response);
    }

    protected void redirect(String url,HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(url);
    }
    
}
