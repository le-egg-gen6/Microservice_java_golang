package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.myproject.product_service.utils.CustomLocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author nguyenle
 * @since 12:28 AM Fri 9/20/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePromotionRequest {

    @JsonProperty("name")
    @NonNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("discountPercentage")
    @NonNull
    private Double discountPercentage;

    @JsonProperty("startDate")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;

}
