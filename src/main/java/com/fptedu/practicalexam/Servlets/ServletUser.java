package com.fptedu.practicalexam.Servlets;

import com.fptedu.practicalexam.Models.Product;
import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.ProductDAO;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletUser", value = "/user")
public class ServletUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Display user info

        response.setContentType("text/html; charset=UTF-8");

        String url = request.getContextPath() + "/account/user.jsp";

        //Get all users
        ArrayList<User> users = UserDAO.getAllUsers();
        request.setAttribute("users", users);
        //Get all products
        ArrayList<Product> products = ProductDAO.getAllProduct();
        request.setAttribute("products", products);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
