package com.fptedu.practicalexam.Servlets;

import com.fptedu.practicalexam.Models.Product;
import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.ProductDAO;
import com.fptedu.practicalexam.Utils.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletUser", value = "/user")
public class ServletUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Display user info

        response.setContentType("text/html; charset=UTF-8");

        String url = request.getContextPath() + "/account/user.jsp";

        User user = (User) request.getSession().getAttribute("user");

        //Update session user if changed
        User user_update = UserDAO.getUser(user.getUsername());

        if (user_update != user) {
            System.out.println("Current user changed information");
            request.getSession().setAttribute("user", user_update);
            request.setAttribute("user", user_update);
        } else {
            request.setAttribute("user", user);
        }

        //Get all products
        ArrayList<Product> products = ProductDAO.getAllProduct();
        request.setAttribute("products", products);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
