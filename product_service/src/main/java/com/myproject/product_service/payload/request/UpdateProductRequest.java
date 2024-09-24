package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
public class UpdateProductRequest {

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

    @JsonProperty("stockQuantity")
    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0!")
    private Integer stockQuantity;

    @JsonProperty("likesCount")
    @Min(value = 0, message = "Like count must be greater than or equal to 0!")
    private Integer likesCount;

}
