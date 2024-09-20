package com.myproject.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
    private List<Product> products;

}
