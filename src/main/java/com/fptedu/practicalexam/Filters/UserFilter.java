package com.fptedu.practicalexam.Filters;

import com.fptedu.practicalexam.Models.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/user", "/user/*"})
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
       //Check if user logged in
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            String errorUrl = ((HttpServletRequest) request).getContextPath() + "/";
            ((HttpServletResponse) response).sendRedirect(errorUrl);
        } else if (user.getAdmin()) {
            String errorUrl = ((HttpServletRequest) request).getContextPath() + "/admin";
            ((HttpServletResponse) response).sendRedirect(errorUrl);
        } else {
            chain.doFilter(request, response);
        }
    }
}
