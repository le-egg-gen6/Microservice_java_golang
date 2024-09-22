package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author nguyenle
 * @since 2:22 AM Fri 9/13/2024
 */
@Entity
@Table(
        name = "t_category",
        indexes = {
                @Index(columnList = "name")
        }
)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends AbstractBaseEntity {

    @Column(name = "name",nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(
            mappedBy = "categories",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @Setter(AccessLevel.NONE)
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getCategories().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getCategories().remove(this);
    }

    public void setProducts(List<Product> products) {
        this.products.forEach(product -> product.getCategories().remove(this));
        this.products.clear();
        if (products != null) {
            products.forEach(this::addProduct);
        }
    }
}
