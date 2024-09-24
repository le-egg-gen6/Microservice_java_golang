package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.dto.CategoryDTO;
import jakarta.validation.constraints.NotEmpty;
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
    @NonNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    @NonNull
    private Long price;

    @JsonProperty("image")
    private MultipartFile image;

}
