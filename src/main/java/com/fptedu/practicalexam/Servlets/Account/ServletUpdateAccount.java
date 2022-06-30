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
            //Redirect to admin page
            String url = request.getContextPath() + "/";
            response.sendRedirect(url);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        User currentUser = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        if (action.equals("Update")) {
            if (currentUser.getAdmin()) {
                String[] role = request.getParameterValues("admin");
                boolean admin = role != null;
                String[] statuses = request.getParameterValues("status");
                boolean status = statuses != null;

                //Check if user update his own account
                if(username.equals(currentUser.getUsername())) { //If true, return to admin page
                    String url = request.getContextPath() + "/admin";
                    response.sendRedirect(url);
                } else {
                    User user = new User(username, password, fullname, status, admin);
                    UserDAO.updateUser(username, user);
                    String url = request.getContextPath() + "/admin";
                    response.sendRedirect(url);
                }
            } else {
                User user = UserDAO.getUser(username);
                user.setFullname(fullname);
                user.setPassword(password);
                UserDAO.updateUser(username, user);
                //Redirect to user page
                String url = request.getContextPath() + "/user";
                response.sendRedirect(url);
            }
        }
    }
}
