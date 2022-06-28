package com.fptedu.practicalexam.Servlets.product;

import com.fptedu.practicalexam.Utils.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeleteProduct", value = "/admin/product/delete")
public class ServletDeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("productId");

        //Delete product
        ProductDAO.deleteProduct(id);

        //Back to admin page
        response.sendRedirect("/admin");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
