package com.cart.service;

import com.cart.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    ResponseEntity<?> addToCartByUserId(Cart cart, Long userId);

    List<Cart> viewCartByUser(Long userId);
}
