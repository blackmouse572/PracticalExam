package com.fptedu.practicalexam.Filters;

import com.fptedu.practicalexam.Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "HomeFilter", urlPatterns = {"/", "/views/*"})
public class HomeFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //If path is /views/home.jsp, redirect to /
        if (((HttpServletRequest) request).getRequestURI().equals("/views/home.jsp")) {
            String url = ((HttpServletRequest) request).getContextPath() + "/";
            ((HttpServletResponse) response).sendRedirect(url);
            return;
        }
        //Check if user is logged in
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            String errorUrl = ((HttpServletRequest) request).getContextPath() + "/views/home.jsp";
            ((HttpServletResponse) response).sendRedirect(errorUrl);
            return;
        } else if (user.getAdmin()) {
            //Redirect to admin page
            String adminUrl = ((HttpServletRequest) request).getContextPath() + "/admin";
            ((HttpServletResponse) response).sendRedirect(adminUrl);
            return;
        } else {
            //Redirect to user page
            String userUrl = ((HttpServletRequest) request).getContextPath() + "/user";
            ((HttpServletResponse) response).sendRedirect(userUrl);
        }
        chain.doFilter(request, response);
    }
}
