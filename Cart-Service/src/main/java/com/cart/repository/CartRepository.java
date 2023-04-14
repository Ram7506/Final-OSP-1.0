package com.cart.repository;

import com.cart.pojo.Product;
import com.cart.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cart.entity.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    List<Cart> findByUserId(Long userId);

    @Query(value = "select * from product where product_id=:pId",nativeQuery = true)
    Product checkProductId(@Param("pId") Long productId);


    @Query(value = "select * from user where user_Id=:uId",nativeQuery = true)
    User getUser(@Param("uId") Long userId);

    @Query(value = "select * from cart where user_id=:uId and product_id=:pId",nativeQuery = true)
    Cart checkCartProductOfUser(@Param("uId") Long userId, @Param("pId") Long productId);


    @Query(value = "select cart_id from cart where user_id=:uId",nativeQuery = true)
    List<Integer> getAllCartId(@Param("uId") Long userId);


    @Query(value = "select * from cart where user_id=:uId",nativeQuery = true)
    List<Cart> getCartById(@Param("uId") Long userId);
}
