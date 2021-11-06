package com.hashkart.productManagement.service;

import com.hashkart.productManagement.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void save(Product product);
}
