package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
public class Category extends AbstractBaseEntity{

    @Column(name = "name",nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Product> products;

}
