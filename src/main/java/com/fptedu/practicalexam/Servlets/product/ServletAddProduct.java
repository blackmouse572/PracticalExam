package com.fptedu.practicalexam.Servlets.product;

import com.fptedu.practicalexam.Models.Product;
import com.fptedu.practicalexam.Models.User;
import com.fptedu.practicalexam.Utils.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
@MultipartConfig() // upload file's size up to 16MB
@WebServlet(name = "ServletAddProduct", value = "/admin/product/add")
public class ServletAddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "Add");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/product/add.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String productName = request.getParameter("productName");
        Float price = Float.valueOf(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        //Get image path and write to webapp/images
        Part filePart = request.getPart("productImage");
        String fileName = filePart.getSubmittedFileName();
        String filePath = request.getServletContext().getRealPath("/images/" + fileName);
        filePart.write(filePath);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUsername();

        Date dateCreate = new java.sql.Date(new java.util.Date().getTime());


        //remove file name extension
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));

        Product product = new Product(productName, price, quantity, fileNameWithoutExtension, userName, dateCreate);

        //Check if product name is exist\
        if (ProductDAO.getProduct(productName) != null) {
            request.setAttribute("error", "Product name is exist");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/product/add.jsp");
            requestDispatcher.forward(request, response);
        } else {
            ProductDAO.addProduct(product);
            response.sendRedirect("/admin/product/list");
        }
        String adminUrl = request.getContextPath() + "/admin";
        response.sendRedirect(adminUrl);
    }
}
