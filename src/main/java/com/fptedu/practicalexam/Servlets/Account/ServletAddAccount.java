package com.fptedu.practicalexam.Servlets.Account;

import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.AdminDAO;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAddAccount", value = "/admin/account/add")
public class ServletAddAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "Add");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/account/add.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String[] role = request.getParameterValues("admin");
        boolean admin = role != null;

        String[] statuses = request.getParameterValues("status");
        boolean status = statuses != null;

        User user = new User(username, password, fullname, status, admin);
        //Check if username is exist
        if (UserDAO.getUser(username).getUsername() != null) {
            request.setAttribute("error", "Username is exist");
            request.setAttribute("action", "Add");
            request.setAttribute("username", username);
            request.setAttribute("fullname", fullname);
            request.setAttribute("admin", admin);
            request.setAttribute("status", status);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/account/add.jsp");
            requestDispatcher.forward(request, response);
        } else {
            AdminDAO.addAccount(user);
            String adminUrl = request.getContextPath() + "/admin";
            response.sendRedirect(adminUrl);
        }
    }
}
