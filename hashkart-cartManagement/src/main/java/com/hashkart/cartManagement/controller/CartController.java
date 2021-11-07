package com.hashkart.cartManagement.controller;

import com.hashkart.cartManagement.model.Cart;
import com.hashkart.cartManagement.model.CartProducts;
import com.hashkart.cartManagement.model.Product;
import com.hashkart.cartManagement.repo.CartProductRepo;
import com.hashkart.cartManagement.repo.CartRepo;
import com.hashkart.cartManagement.service.OrderService;
import com.hashkart.cartManagement.serviceproxy.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private ProductServiceProxy productServiceProxy;

    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getAll() {
        return ResponseEntity.ok().body(cartRepo.findAll());
    }

    @PostMapping("/cart")
    public Cart save(@RequestBody Cart cart) {
        return cartRepo.save(cart);
    }

    @PostMapping("/cart/{cartId}/product/{productId}")
    public void addProductInCart(@PathVariable int cartId,
                                 @PathVariable int productId) {
        Optional<Cart> cartInfo = cartRepo.findById(cartId);
        // fetch product by it's id.. using proxy..
        Product product = productServiceProxy.getProductById(productId);

        if (cartInfo.isPresent()) {
            CartProducts cartProducts = new CartProducts(
                    product.getProductId(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getQuantity(),
                    cartInfo.get());
            cartProductRepo.save(cartProducts);
        }
    }
}
