package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

/**
 * @author nguyenle
 */
@Entity
@Table(
        name = "t_promotion",
        indexes = {

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
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
    private Set<Product> products;

}
