/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.util.Date;

/**
 *
 * @author linhn
 */
public class ProductDTO {

    private String productID;
    private String productName;
    private String image;
    private String categoryID;
    private double price;
    private double quantity;
    private Date importDate;
    private Date usingDate;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, String image, String categoryID, double price, double quantity, Date importDate, Date usingDate) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
        this.usingDate = usingDate;
    }

    public ProductDTO(String productID, String productName, String image, double price, double quantity) {
        this.productID = productID;
        this.image = image;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(Date usingDate) {
        this.usingDate = usingDate;
    }

}
