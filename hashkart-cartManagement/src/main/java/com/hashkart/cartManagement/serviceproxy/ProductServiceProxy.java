package com.hashkart.cartManagement.serviceproxy;

import com.hashkart.cartManagement.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="hashkart-productManagment")
public interface ProductServiceProxy {

    /**
     * Call to product-management microservice through feign-client.
     *
     * @param id
     * @return Product object corresponding to id.
     */
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable int id);

    @PutMapping("/products/{id}/quantity/{quantity}")
    Product updateProductQuantity(@PathVariable int id,@PathVariable int quantity);
}
