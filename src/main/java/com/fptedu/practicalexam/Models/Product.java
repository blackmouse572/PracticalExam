package com.fptedu.practicalexam.Models;

import java.util.Date;

public class Product {
    private String productId;
    private String productName;
    private Float price;
    private int quantity;
    private String productImage;
    private String userCreate;
    private Date dateCreate;

    public Product() {

    }

    public Product( String productName, Float price, int quantity, String productImage, String userCreate, Date dateCreate) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.productImage = productImage;
        this.userCreate = userCreate;
        this.dateCreate = dateCreate;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}


