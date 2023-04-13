package com.product.Service;

import com.product.Model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void deleteById(long productId);

    Product searchProductById(long productId);

    List<Product> viewAllProducts();

    List<Product> searchProductByCategory(String productCategory);

    List<Product> searchProductByName(String productName);

    String updateProduct(Product product,long productId);


    Long getProductPriceById(Long productId);
}
