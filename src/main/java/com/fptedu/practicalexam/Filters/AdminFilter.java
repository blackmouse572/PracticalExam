package com.fptedu.practicalexam.Filters;

import com.fptedu.practicalexam.Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccountFilter", urlPatterns = {"/admin", "/admin/*"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) {


    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //Check if sessionUser login or not

        HttpSession session = ((HttpServletRequest) request).getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            String errorUrl = ((HttpServletRequest) request).getContextPath() + "/";
            ((HttpServletResponse) response).sendRedirect(errorUrl);
        } else if (!sessionUser.getAdmin()) { //If sessionUser is not admin
            System.out.println("User is not admin");

            if (((HttpServletRequest) request).getRequestURI().contains("/admin/account/edit")) { //Check if sessionUser is trying to access add account page
                System.out.println("User is trying to access edit account page");
                String username = request.getParameter("username");
                if (username.equals(sessionUser.getUsername())) { //Check if sessionUser is trying to edit himself
                    chain.doFilter(request, response);
                } else {
                    String errorUrl = ((HttpServletRequest) request).getContextPath() + "/user";
                    ((HttpServletResponse) response).sendRedirect(errorUrl);
                    return;
                }
            }
            if (((HttpServletRequest) request).getRequestURI().contains("/admin/account/delete")) { //Check if sessionUser is trying to access delete account page
                System.out.println("User is trying to access delete account page");
                String username = request.getParameter("username");
                if (username.equals(sessionUser.getUsername())) { //Check if sessionUser is trying to delete himself
                    System.out.println("User is trying to delete himself");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("User is trying to delete other user");
                    String errorUrl = ((HttpServletRequest) request).getContextPath() + "/user";
                    ((HttpServletResponse) response).sendRedirect(errorUrl);
                }
            }
            //process if url /admin/logout even sessionUser is not admin
            if (((HttpServletRequest) request).getRequestURI().contains("/admin/logout")) {
                chain.doFilter(request, response);
            }
        } else {
            //R
            chain.doFilter(request, response);
        }
    }
}
