package com.cart.feignService;

import com.cart.pojo.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "Cart-User", url = "http://localhost:8888")
public interface ICartUserService {

    String SERVICE = "Cart-User";

    @GetMapping("/getMyDetails/{userId}")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "fallBackMethodForGetUser")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId);

    default ResponseEntity<Object> fallBackMethodForGetUser(Throwable throwable) {
        return new ResponseEntity<>("User Service is down !!!!", HttpStatus.OK);
    }
}
