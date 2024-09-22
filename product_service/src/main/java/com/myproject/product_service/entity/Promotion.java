package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nguyenle
 */
@Entity
@Table(
        name = "t_promotion",
        indexes = {
                @Index(columnList = "name"),
                @Index(columnList = "start_date"),
                @Index(columnList = "end_date")
        }
)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToMany(
            mappedBy = "promotions",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @Setter(AccessLevel.NONE)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getPromotions().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getPromotions().remove(this);
    }

    public void setProducts(List<Product> products) {
        this.products.forEach(product -> product.getPromotions().remove(this));
        this.products.clear();
        if (products != null) {
            products.forEach(this::addProduct);
        }
    }
}
