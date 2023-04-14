package com.cart.controller;

import com.cart.entity.Cart;
import com.cart.feignService.ICartProductService;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/viewCartByUser/{userId}")
    public ResponseEntity<?> viewCartByUser(@PathVariable("userId") Long userId) {

        try {
            List<Cart> cartList=this.cartService.viewCartByUser(userId);
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON");
        }


    }


}
