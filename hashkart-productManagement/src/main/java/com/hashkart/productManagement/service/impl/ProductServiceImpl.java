package com.hashkart.productManagement.service.impl;

import com.hashkart.productManagement.model.Product;
import com.hashkart.productManagement.repo.ProductRepo;
import com.hashkart.productManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Product getProductById(int id) {
        return productRepo.findByProductId(id);
    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product updateProductQuantity(int id, int quantity) {
        Optional<Product> product = Optional.ofNullable(productRepo.findByProductId(id));
        if (product.get().getQuantity() >= quantity) {
            product.get().setQuantity(product.get().getQuantity() - quantity);
            return productRepo.save(product.get());
        } else {
            return product.get();
        }
    }
}
