package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nguyenle
 * @since 9:47 PM Thu 9/19/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name is required!")
    private String name;

    @JsonProperty("description")
    private String description = "";

    @JsonProperty("price")
    @NonNull
    @Min(value = 0, message = "Price must be greater than or equal to 0!")
    private Long price;

    @JsonProperty("image")
    private MultipartFile image;

    @JsonProperty("categoryIds")
    private List<Long> categoryIds;

    @JsonProperty("promotionIds")
    private List<Long> promotionIds;

}
