package com.fptedu.practicalexam.Servlets.Account;

import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateAccount", value = "/admin/account/edit")
public class ServletUpdateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        if (username == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin");
            requestDispatcher.forward(request, response);
        }


        User user = UserDAO.getUser(username);
        request.setAttribute("action", "Update");
        request.setAttribute("username", user.getUsername());
        request.setAttribute("fullname", user.getFullname());
        request.setAttribute("admin", user.getAdmin());
        request.setAttribute("status", user.getStatus());
        request.setAttribute("action", "Update");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/account/add.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        System.out.println("action: " + action + " username: " + username + " password: " + password + " fullname: " + fullname + " admin: " + admin + " status: " + status);

        if (action.equals("Update")) {
            User user = new User(username, password, fullname, admin, status);
            UserDAO.updateUser(username, user);

        }
        User user = (User) request.getSession().getAttribute("user");        //If user session is admin then redirect to admin page
        if  (user.getAdmin()) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/user");
        }
        return;
    }
}
