package com.myproject.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myproject.product_service.utils.CustomLocalDateTimeDeserializer;
import com.myproject.product_service.utils.CustomLocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author nguyenle
 * @since 6:27 AM Tue 9/17/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("discountPercentage")
    private Double discountPercentage;

    @JsonProperty("startDate")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;

}
