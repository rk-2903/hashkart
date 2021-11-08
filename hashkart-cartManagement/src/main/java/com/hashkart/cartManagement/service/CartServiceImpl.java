package com.hashkart.cartManagement.service;

import com.hashkart.cartManagement.model.Cart;
import com.hashkart.cartManagement.model.CartProducts;
import com.hashkart.cartManagement.model.Product;
import com.hashkart.cartManagement.repo.CartProductRepo;
import com.hashkart.cartManagement.repo.CartRepo;
import com.hashkart.cartManagement.serviceproxy.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Value("${cart.discount.percent}")
    private int cartDiscountPercentage;

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
            CartProducts cartProducts = new CartProducts(
                    product.getProductId(),
                    product.getProductName(),
                    product.getPrice()*quantity,
                    product.getDescription(),
                    quantity,
                    cartInfo.get());
            try {
                cartProductRepo.save(cartProducts);
                productServiceProxy.updateProductQuantity(productId, quantity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            int totalCartAmount = cartInfo.get()
                    .getCartProducts()
                    .stream()
                    .mapToInt(CartProducts::getPrice)
                    .sum();
            cartInfo.get().setTotalAmount(totalCartAmount);
            if (totalCartAmount >= 10000) {
                cartInfo.get().setDiscountPercent(cartDiscountPercentage);
                cartInfo.get().setDiscountedAmount(totalCartAmount-((cartDiscountPercentage/100)* totalCartAmount));
            } else {
                cartInfo.get().setDiscountPercent(0);
                cartInfo.get().setDiscountedAmount(totalCartAmount);
            }
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

    @Override
    public void deleteById(int id) {
        cartRepo.deleteById(id);
    }

}
