package com.hashkart.cartManagement.controller;

import com.hashkart.cartManagement.model.Cart;
import com.hashkart.cartManagement.model.CartProducts;
import com.hashkart.cartManagement.repo.CartProductRepo;
import com.hashkart.cartManagement.repo.CartRepo;
import com.hashkart.cartManagement.service.OrderService;
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


    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getAll() {
        return ResponseEntity.ok().body(cartRepo.findAll());
    }

    @PostMapping("/cart")
    public Cart save(@RequestBody Cart cart) {
        return cartRepo.save(cart);
    }

    @PostMapping("/add-product-in-cart")
    public void addProductInCart(@RequestParam int id ) {
        Optional<Cart> cartInfo = cartRepo.findById(id);

        // fetch product by it's id.. using proxy..
        CartProducts cartProducts = new CartProducts(1, "shirt", "100",
                "new shirt", 111111111L,
                cartInfo.get());

        cartProductRepo.save(cartProducts);
    }


}
