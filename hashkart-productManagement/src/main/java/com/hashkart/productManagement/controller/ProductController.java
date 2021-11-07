package com.hashkart.productManagement.controller;

import com.hashkart.productManagement.model.Product;
import com.hashkart.productManagement.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
    }

    /**
     *
     * @param id
     * @param quantity
     * @return Updated product if the quantity of product is available.
     */
    @PatchMapping("/product/{id}/quantity/{quantity}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable int id,
                                                         @PathVariable int quantity) {
        return ResponseEntity.ok().body(productService.updateProductQuantity(id, quantity));
    }
}
