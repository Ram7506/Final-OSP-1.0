package com.common.controller;

import com.common.entity.User;
import com.common.feign.IProductService;
import com.common.pojo.Product;
import com.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommonController {


    @Autowired
    private IProductService iProductService;

    @Autowired
    private UserService userService;


    //1. To get all the products from Product Service.

    @GetMapping("/getAllProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllProduct () {
        ResponseEntity<List<Product>> listOfProducts = this.iProductService.findAllProduct();
        return listOfProducts;
    }
    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getMyDetails/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getMyDetails (@PathVariable("userId") Long userId) {
        System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        User user = this.userService.findbyId(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



    //2. add a product to database

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addProduct(@RequestBody Product product) {
        try {
            ResponseEntity<Product> product1 = this.iProductService.addProduct(product);
            return product1;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON BODY");
        }
    }

    //3. delete product by productId

    @DeleteMapping("/deleteProduct/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProductById(@PathVariable("productId") long productId) {
        try {
            String response = this.iProductService.deleteById(productId);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //4. search product by productId

    @GetMapping("/searchProductById/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Product> searchProductById(@PathVariable("productId") long productId) {
        try {
            ResponseEntity<Product> product = this.iProductService.searchProductById(productId);
            return product;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //5. search product by name
    @GetMapping("/searchProductByName/{productName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity searchProductByName(@PathVariable("productName") String productName) {
        try {
            ResponseEntity<List<Product>> ProductList = this.iProductService.searchProductByName(productName);
            return ProductList;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //6. search product by category
    @GetMapping("/searchProductByCategory/{productCategory}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> searchProductByCategory(@PathVariable("productCategory") String productCategory) {
        try {
            ResponseEntity<List<Product>> ProductList = this.iProductService.searchProductByCategory(productCategory);
            return ProductList;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //7. update product by productId (Only Admin has access to update the product..)
    @PutMapping("/updateByProductId/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateByProductId(@RequestBody Product product, @PathVariable("productId") long productId) {

        try {
            String msg = this.iProductService.updateProduct(product, productId);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //8. User or Admin can update his details only address,username,password and phoneNo

    @PutMapping("/updateUserById/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<String> updateUserById(@RequestBody User user, @PathVariable("userId") long userId) {
        try {
            String msg = this.userService.updateUser(user,userId);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // For Testing
    @GetMapping("/testCase")
    @PreAuthorize("hasRole('ADMIN')")
    public String testCase () {
        String s = iProductService.testing();
        return s;
    }
}
