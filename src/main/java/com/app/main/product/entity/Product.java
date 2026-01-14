package com.app.main.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String sku;

    @Column(name = "sku_no", nullable = false, updatable = false, unique = true)
    private Long skuNo;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 80)
    private String brand;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(length = 20)
    private String unit;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, updatable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = Instant.now();
        this.updatedOn = Instant.now();
        this.sku = "SKU" + String.format("%03d", this.skuNo);
    }

    @PostPersist
    public void postPersist() {
        this.createdOn = Instant.now();
    }
}

