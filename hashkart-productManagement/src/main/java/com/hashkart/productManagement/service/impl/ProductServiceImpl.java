package com.hashkart.productManagement.service.impl;

import com.hashkart.productManagement.model.Product;
import com.hashkart.productManagement.repo.ProductRepo;
import com.hashkart.productManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }
}
