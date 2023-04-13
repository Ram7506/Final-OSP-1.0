package com.product.Controller;

import com.product.Model.Product;
import com.product.Service.ProductService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private SessionFactory sessionFactory;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    // add a product to database
    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product) {
        try {
            LOGGER.info("POST /product/addProduct");
            Product product1 = this.productService.save(product);
            return ResponseEntity.ok(product1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON BODY");
        }
    }

    // delete product by productId
    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProductById(@PathVariable("productId") long productId) {
        try {
            LOGGER.info("DELETE /product/deleteProduct/" + productId);
            System.out.println("------------------------------");
            this.productService.deleteById(productId);
            return "Product Deleted Successfully...";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // search product by productId

    @GetMapping("/searchProductById/{productId}")
    public ResponseEntity<Product> searchProductById(@PathVariable("productId") long productId) {
        try {
            LOGGER.info("GET /product/searchProductById/" + productId);
            Product product = this.productService.searchProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // search product by name
    @GetMapping("/searchProductByName/{productName}")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable("productName") String productName) {
        try {
            LOGGER.info("GET /product/searchProductByName/" + productName);
            List<Product> ProductList = this.productService.searchProductByName(productName);
            return new ResponseEntity<>(ProductList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // search product by category
    @GetMapping("/searchProductByCategory/{productCategory}")
    public ResponseEntity<List<Product>> searchProductByCategory(@PathVariable("productCategory") String productCategory) {
        try {
            LOGGER.info("GET /product/searchProductByCategory/" + productCategory);
            List<Product> ProductList = this.productService.searchProductByCategory(productCategory);
            return new ResponseEntity<>(ProductList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get all product
    @GetMapping("/viewAllProducts")
    public ResponseEntity<List<Product>> viewAllProducts() {
        LOGGER.info("GET /product/viewAllProducts");
        List<Product> ProductList = this.productService.viewAllProducts();
        return new ResponseEntity<>(ProductList, HttpStatus.OK);
    }


    // update product by productId (Only Provider has access to update the product..)
    @PutMapping("/updateByProductId/{productId}")
    public ResponseEntity<?> updateByProductId(@RequestBody Product product, @PathVariable("productId") long productId) {

        try {
            LOGGER.info("UPDATE /product/updateByProductId/" + productId);
            String msg = productService.updateProduct(product, productId);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getProductPrice/{productId}")
    public Long getProductPrice(@PathVariable("productId") Long productId) {
        LOGGER.info("GET /getProductPrice/"+productId);
        System.out.println();
        return this.productService.getProductPriceById(productId);
    }

    @GetMapping("/test")
    public String test(){
        return "This is test Method !!!";
    }

}
