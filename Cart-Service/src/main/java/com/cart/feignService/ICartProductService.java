package com.cart.feignService;

import com.cart.pojo.Product;
import com.cart.pojo.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//@Component
@FeignClient(value = "Cart", url = "http://localhost:8383")
public interface ICartProductService {


    String SERVICE = "Cart";

    @GetMapping("/searchProductById/{productId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForGetProductById")
    public ResponseEntity<Product> checkProductId(@PathVariable("productId") Long productId);

    default ResponseEntity<Object> fallBackMethodForGetProductById(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }

    @GetMapping(value = "/searchProductById/{productId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForGetProduct")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId);

    default ResponseEntity<Object> fallBackMethodForGetProduct(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }



}
