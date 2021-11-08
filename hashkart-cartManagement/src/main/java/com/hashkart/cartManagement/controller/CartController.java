package com.hashkart.cartManagement.controller;

import com.hashkart.cartManagement.model.Cart;
import com.hashkart.cartManagement.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getAll() {
        return ResponseEntity.ok().body(cartService.findAll());
    }

    @PostMapping("/cart")
    public Cart save(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @PostMapping("cart/{cartId}/user/{userId}/product/{productId}/quantity/{quantity}")
    public String addProductInCart(@PathVariable int cartId,
                                   @PathVariable int userId,
                                   @PathVariable int productId,
                                   @PathVariable int quantity) throws Exception {
        return cartService.addProductInCart(userId, cartId, productId, quantity);
    }

    @GetMapping("/cart/{cartId}/total-amount")
    public int calculateTotalAmountOfCart(@PathVariable int cartId) {
        return cartService.calculateTotalAmountOfCart(cartId);
    }

}
