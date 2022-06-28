package com.fptedu.practicalexam.Servlets.Account;

import com.fptedu.practicalexam.Utils.AdminDAO;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeleteAccount", value = "/admin/account/delete")
public class ServletDeleteAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");

        //Delete user
        AdminDAO.deleteUser(username);

        //Back to admin page
        response.sendRedirect("/admin");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
