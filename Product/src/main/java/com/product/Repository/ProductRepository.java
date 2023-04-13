package com.product.Repository;

import com.product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByProductCategory(@Param("pc") String productCategory);
    List<Product> findByProductName(@Param("pn") String productName);

    @Query(value = "select product_price from product where product_id=:pId",nativeQuery = true)
    Long getProductPriceById(@Param("pId") Long productId);

}
