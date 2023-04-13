package com.common.feign;

import com.common.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Component
@FeignClient(value = "Common", url = "http://localhost:8383")
public interface IProductService {

    String SERVICE = "Common";

    @GetMapping("/viewAllProducts")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForGetAllProduct")
    public ResponseEntity<List<Product>> findAllProduct();

    default ResponseEntity<Object> fallBackMethodForGetAllProduct(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }


    @PostMapping("/addProduct")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodAddProduct")
    public ResponseEntity<Product> addProduct(Product product);

    default ResponseEntity<Object> fallBackMethodAddProduct(Product product,Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodDeleteProduct")
    public String deleteById(@PathVariable("productId") Long productId);

    default String fallBackMethodDeleteProduct(Throwable throwable) {
        return "Product Service is down !!!";
    }

    @GetMapping("/searchProductById/{productId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForSearchProductById")
    public ResponseEntity<Product> searchProductById(@PathVariable("productId") long productId);

    default ResponseEntity<Object> fallBackMethodForSearchProductById(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }

    @GetMapping("/searchProductByName/{productName}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForSearchProductByName")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable("productName") String productName);

    default ResponseEntity<?> fallBackMethodForSearchProductByName(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }

    @GetMapping("/searchProductByCategory/{productCategory}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForSearchProductByCategory")
    public ResponseEntity<List<Product>> searchProductByCategory(@PathVariable("productCategory") String productCategory);

    default ResponseEntity<?> fallBackMethodForSearchProductByCategory(Throwable throwable) {
        return new ResponseEntity<>("Product Service is down !!!!", HttpStatus.OK);
    }

    @PutMapping("/updateByProductId/{productId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForUpdateByProductId")
    public String updateProduct(@RequestBody Product product, @PathVariable("productId") long productId);

    default String fallBackMethodForUpdateByProductId(Throwable throwable) {
        return "Product Service down !!!!";
    }




    // For testing
    @GetMapping("/test")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForTest")
    public String testing();


    default String fallBackMethodForTest(Throwable throwable) {
        return "Product Service down !!!!";
    }

}
