package com.fptedu.practicalexam.Servlets.product;

import com.fptedu.practicalexam.Models.Product;
import com.fptedu.practicalexam.Utils.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig() // upload file's size up to 16MB
@WebServlet(name = "ServletUpdateProduct", value = "/admin/product/edit")
public class ServletUpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId == null) {
            response.sendRedirect("/admin");
        }
        Product product = ProductDAO.getProduct(productId);
        request.setAttribute("productId", productId);
        request.setAttribute("productName", product.getProductName());
        request.setAttribute("price", product.getPrice());
        request.setAttribute("quantity", product.getQuantity());
        request.setAttribute("userCreated", product.getUserCreate());
        request.setAttribute("createAt", product.getDateCreate());

        request.setAttribute("action", "Update");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/product/add.jsp");
        requestDispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String userCreated = request.getParameter("userCreated");

        Part filePart = request.getPart("productImage");
        //write image to server
        Product oldProduct = ProductDAO.getProduct(productId);

//        remove old image from server
        if (oldProduct.getProductImage() != null) {
            String oldImage = oldProduct.getProductImage();
            String path = request.getServletContext().getRealPath("/");
            String filePath = path + "\\images\\" + oldImage;
            System.out.println(filePath);
            java.io.File file = new java.io.File(filePath);
            file.delete();
        }

        //write new image to server
        String fileName = filePart.getSubmittedFileName();
        String path = request.getServletContext().getRealPath("/");
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
        String filePath = path + "\\images\\" + fileNameWithoutExtension + oldProduct.getProductId() + ".jpg";
        System.out.println(filePath);
        filePart.write(filePath);


        if (action.equals("Update")) {
            Product product = new Product(productName, price, quantity, fileNameWithoutExtension + oldProduct.getProductId(), userCreated, oldProduct.getDateCreate());
            ProductDAO.updateProduct(productId, product);
        }
        response.sendRedirect("/admin");
    }
}
