package com.cart.service;

import com.cart.entity.Cart;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCartByUserId(Cart cart, Long userId);
}
