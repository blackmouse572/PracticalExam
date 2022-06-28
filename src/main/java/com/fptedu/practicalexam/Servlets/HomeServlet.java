package com.fptedu.practicalexam.Servlets;

import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/HomePage")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");
        String remember = request.getParameter("remember-me");

        User user = UserDAO.getUser(username);
        if (user.getUsername() == null || !user.getPassword().equals(password)) {
            request.setAttribute("error", "Username or password is incorrect");

            String homeUrl = request.getContextPath() + "/views/home.jsp";
            request.getRequestDispatcher(homeUrl).forward(request, response);
        }
        else if  (user.getUsername().equals(username) && user.getPassword().equals(password) ) {
            HttpSession session = request.getSession();
            if(!user.getStatus()) {
                request.setAttribute("error", "Your account is not active");
                String homeUrl = request.getContextPath() + "/views/home.jsp";
                request.getRequestDispatcher(homeUrl).forward(request, response);
            }
            session.setAttribute("user", user);
            System.out.println("User: " + user.getUsername() + " logged in at " + new java.util.Date());
            System.out.println("Session ID: " + session.getId());

            if (remember != null) {
                session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            else {
                session.setMaxInactiveInterval(60 * 60 * 24);
            }
            System.out.println("Session timeout: " + session.getMaxInactiveInterval()+ "(sec) with account"+session.getAttribute("user"));

            if (user.getAdmin()) {
               String adminUrl = request.getContextPath() + "/admin";
                response.sendRedirect(adminUrl);
            } else {
                String userUrl = request.getContextPath() + "/user";
                response.sendRedirect(userUrl);
            }
        }
    }
}
