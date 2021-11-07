package com.hashkart.cartManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartProducts {

    @Id
    private int productId;
    private String productName;
    private int price;
    private String description;
    private int quantity;

    @ManyToOne
    @JsonIgnore
    public Cart cart;

    public CartProducts() {
    }

    public CartProducts(int productId, String productName, int price, String description, int quantity, Cart cart) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.cart = cart;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
