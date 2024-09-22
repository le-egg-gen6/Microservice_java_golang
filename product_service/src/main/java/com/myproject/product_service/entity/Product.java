package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nguyenle
 */
@Entity
@Table(
        name = "t_product",
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
public class Product extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "likes_count")
    private Integer likesCount;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Setter(AccessLevel.NONE)
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "product_promotion",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    @Setter(AccessLevel.NONE)
    private List<Promotion> promotions = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProducts().remove(this);
    }

    public void setCategories(List<Category> categories) {
        this.categories.forEach(category -> category.getProducts().remove(this));
        this.categories.clear();
        if (categories != null) {
            categories.forEach(this::addCategory);
        }
    }

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
        promotion.getProducts().add(this);
    }

    public void removePromotion(Promotion promotion) {
        promotions.remove(promotion);
        promotion.getProducts().remove(this);
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions.forEach(promotion -> promotion.getProducts().remove(this));
        this.promotions.clear();
        if (promotions != null) {
            promotions.forEach(this::addPromotion);
        }
    }
}
