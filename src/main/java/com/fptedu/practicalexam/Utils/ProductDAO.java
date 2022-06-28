package com.fptedu.practicalexam.Utils;

import com.fptedu.practicalexam.Models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    private static Connection con;

    public static void updateProduct(String oldProductID, Product newProduct) {
        con = DBUtils.getConnection();
        String query = "UPDATE [dbo].[product] SET [productName] = '" + newProduct.getProductName() + "', [price] = '" + newProduct.getPrice() + "', [quantity] = '" + newProduct.getQuantity() + "', [productImage] = '" + newProduct.getProductImage() + "', [userCreate] = '" + newProduct.getUserCreate() + "', [dateCreate] = '" + newProduct.getDateCreate() + "' WHERE [productId] = '" + oldProductID + "'";

         DBUtils.executeQuery(query, con);
        DBUtils.closeConnection(con);
    }

    public static void deleteProduct(String productID) {
        con = DBUtils.getConnection();
        String query = "DELETE FROM [dbo].[product] WHERE [productId] = '" + productID + "'";
        DBUtils.executeUpdate(query, con);
        DBUtils.closeConnection(con);
    }

    public static ArrayList<Product> getAllProduct() {
        con = DBUtils.getConnection();
        String query = "SELECT * FROM [dbo].[product]";
        ResultSet rs = DBUtils.executeQuery(query, con);
        ArrayList<Product> products = new ArrayList<>();
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setProductImage(rs.getString("productImage") + ".jpg");
                product.setUserCreate(rs.getString("userCreate"));
                product.setDateCreate(rs.getDate("dateCreate"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection(con);
        return products;
    }

    public static void addProduct(Product product) {
        con = DBUtils.getConnection();
        String query = "INSERT INTO [dbo].[product] ( [productName], [price], [quantity], [productImage], [userCreate], [dateCreate]) VALUES ('" + product.getProductName() + "', '" + product.getPrice() + "', '" + product.getQuantity() + "', '" + product.getProductImage() + "', '" + product.getUserCreate() + "', '" + product.getDateCreate() + "')";
        DBUtils.executeUpdate(query, con);
        DBUtils.closeConnection(con);

    }

    public static Product getProduct(String productId) {
        con = DBUtils.getConnection();
        String query = "SELECT * FROM [dbo].[product] WHERE [productId] = '" + productId + "'";
        ResultSet rs = DBUtils.executeQuery(query, con);
        try {
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setProductImage(rs.getString("productImage") + ".jpg");
                product.setUserCreate(rs.getString("userCreate"));
                product.setDateCreate(rs.getDate("dateCreate"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        DBUtils.closeConnection(con);
        return null;
    }
}

