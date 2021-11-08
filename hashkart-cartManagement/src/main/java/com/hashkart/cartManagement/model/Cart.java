package com.hashkart.cartManagement.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    private String orderDate;
    private int userId;
    @OneToMany(mappedBy = "cart")
    private List<CartProducts> cartProducts;
    private int totalAmount;
    private int discountPercent;
    private int discountedAmount;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartProducts> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(int discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
}
