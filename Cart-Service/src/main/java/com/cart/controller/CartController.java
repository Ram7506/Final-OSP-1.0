package com.cart.controller;

import com.cart.entity.Cart;
import com.cart.feignService.ICartProductService;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private ICartProductService icartProductService;

    // add to cart by user id
    @PostMapping("/addToCartByUserId/{userId}")
    public ResponseEntity<?> addToCartByUserId(@RequestBody Cart cart, @PathVariable("userId") Long userId) {
        try {
            return this.cartService.addToCartByUserId(cart, userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON");
        }
    }



}
