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
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //Check if user login or not

        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            String errorUrl = ((HttpServletRequest) request).getContextPath() + "/";
            ((HttpServletResponse) response).sendRedirect(errorUrl);
        } else if (!user.getAdmin()) { //If user is not admin
            System.out.println("User is not admin");

            if (((HttpServletRequest) request).getRequestURI().contains("/admin/account/edit")) { //Check if user is trying to access add account page
                System.out.println("Tick ✅");
                String username = ((HttpServletRequest) request).getParameter("username");
                if (username.equals(user.getUsername())) { //Check if user is trying to edit himself
                    chain.doFilter(request, response);
                } else {
                    String errorUrl = ((HttpServletRequest) request).getContextPath() + "/user";
                    ((HttpServletResponse) response).sendRedirect(errorUrl);
                }
            }
            //process if url /admin/logout even user is not admin
            else if (((HttpServletRequest) request).getRequestURI().contains("/admin/logout")) {
                chain.doFilter(request, response);
            } else {
                String errorUrl = ((HttpServletRequest) request).getContextPath() + "/user";
                ((HttpServletResponse) response).sendRedirect(errorUrl);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
