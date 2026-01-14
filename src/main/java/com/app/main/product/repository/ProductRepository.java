package com.app.main.product.repository;

import com.app.main.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT nextval('product_sku_seq')", nativeQuery = true)
    Long getNextSkuNumber();
}
