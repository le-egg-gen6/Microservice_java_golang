package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.myproject.product_service.utils.CustomLocalDateTimeDeserializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author nguyenle
 * @since 7:08 PM Tue 9/24/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePromotionRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name is required!")
    private String name;

    @JsonProperty("description")
    private String description = "";

    @JsonProperty("discountPercentage")
    @NonNull
    @Min(value = 0, message = "Discount percentage is greater than or equal to 0!")
    @Max(value = 100, message = "Discount percentage is smaller than or equal to 0!")
    private Double discountPercentage;

    @JsonProperty("startDate")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;

}
