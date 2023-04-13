package com.product.ServiceImpl;

import com.product.Repository.ProductRepository;
import com.product.Model.Product;
import com.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteById(long productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public Product searchProductById(long productId) {
        return this.productRepository.findById(productId).get();
    }

    @Override
    public List<Product> viewAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> searchProductByCategory(String productCategory) {
        return this.productRepository.findByProductCategory(productCategory);
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return this.productRepository.findByProductName(productName);
    }

    @Override
    public String updateProduct(Product product,long productId) {

        Product newProduct = searchProductById(productId);

        if (newProduct!=null && newProduct.getProductId() == productId) {
            newProduct.setProductName(product.getProductName());
            newProduct.setProductCategory(product.getProductCategory());
            newProduct.setProductDescription(product.getProductDescription());
            newProduct.setProductPrice(product.getProductPrice());
            productRepository.save(newProduct);
            return "Product is Updated....";
        }
        else
            return "Something went wrong...!!!";
    }

    @Override
    public Long getProductPriceById(Long productId) {
        return this.productRepository.getProductPriceById(productId);
    }
}
