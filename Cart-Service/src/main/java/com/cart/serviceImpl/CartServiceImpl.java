package com.cart.serviceImpl;

import com.cart.entity.Cart;
import com.cart.feignService.ICartProductService;
import com.cart.feignService.ICartUserService;
import com.cart.pojo.User;
import com.cart.repository.CartRepository;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private ICartProductService iCartProductService;


    @Autowired
    private ICartUserService iCartUserService;


    @Override
    public ResponseEntity<?> addToCartByUserId(Cart cart, Long userId) {

        Long productId = cart.getProductId();
        ResponseEntity<User> u=null;
        try {
            u = this.iCartUserService.getUser(userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (this.iCartProductService.checkProductId(productId) != null && this.iCartUserService.getUser(userId) != null) {
            Cart singleCart = this.cartRepository.checkCartProductOfUser(userId, productId);
            if (singleCart != null) {
                singleCart.setQuantity(cart.getQuantity());
                singleCart.setProductTotalPrice((long) (cart.getQuantity() * this.iCartProductService.getProduct(productId).getBody().getProductPrice()));
                return ResponseEntity.ok(this.cartRepository.save(singleCart));
            } else {
                cart.setUserId(userId);
                cart.setProductTotalPrice((long) (cart.getQuantity() * this.iCartProductService.getProduct(productId).getBody().getProductPrice()));
                return ResponseEntity.ok(this.cartRepository.save(cart));
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
    }
}
