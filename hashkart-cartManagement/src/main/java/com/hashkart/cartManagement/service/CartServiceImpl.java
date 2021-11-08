package com.hashkart.cartManagement.service;

import com.hashkart.cartManagement.model.Cart;
import com.hashkart.cartManagement.model.CartProducts;
import com.hashkart.cartManagement.model.Product;
import com.hashkart.cartManagement.repo.CartProductRepo;
import com.hashkart.cartManagement.repo.CartRepo;
import com.hashkart.cartManagement.serviceproxy.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartProductRepo cartProductRepo;
    @Autowired
    private ProductServiceProxy productServiceProxy;

    @Override
    public List<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public String addProductInCart(int userId, int cartId, int productId, int quantity) throws Exception {

        Optional<Cart> cartInfo;
        try {
            cartInfo = Optional.ofNullable(cartRepo.findByCartIdAndUserId(cartId, userId));
        } catch (Exception e) {
            throw new Exception("error while fetching data"+ Arrays.toString(e.getStackTrace()));
        }
        // fetch product by it's id.. using proxy..
        Product product = productServiceProxy.getProductById(productId);

        if (product.getQuantity() >= quantity && cartInfo.isPresent()) {
            // check if product is already added in the cart.
            if (cartInfo.get().getCartProducts().stream()
                    .map(CartProducts::getProductId).collect(Collectors.toList()).contains(productId)) {
                cartInfo.get()
                        .setTotalAmount(cartInfo.get().getTotalAmount() -
                                product.getPrice() * product.getQuantity());

                cartInfo.get().getCartProducts().forEach(i -> {
                    if (i.getProductId() == productId) {
                        i.setQuantity(i.getQuantity()+quantity);
                    }
                });
            } else {
                CartProducts cartProducts = new CartProducts(
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getDescription(),
                        quantity,
                        cartInfo.get());
                cartProductRepo.save(cartProducts);
            }
            try {
                productServiceProxy.updateProductQuantity(productId, quantity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            cartInfo.get().setTotalAmount(cartInfo.get().getTotalAmount() + product.getPrice() * product.getQuantity());
            cartRepo.save(cartInfo.get());
            return "Product added to cart.";
        } else if (product.getQuantity() < quantity && product.getQuantity() !=0) {
            return "Maximum "+ product.getQuantity() + " " + product.getProductName() + " can be added int the cart";
        } else {
            return "This product is out of stock.";
        }
    }

    @Override
    public int calculateTotalAmountOfCart(int cartId) {
        Optional<Cart> cart = cartRepo.findById(cartId);
        return cart.get()
                .getCartProducts()
                .stream()
                .mapToInt(CartProducts::getPrice)
                .sum();
    }

}
