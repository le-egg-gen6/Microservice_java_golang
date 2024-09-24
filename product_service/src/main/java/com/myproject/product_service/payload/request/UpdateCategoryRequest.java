package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * @author nguyenle
 * @since 7:07 PM Tue 9/24/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name is required!")
    private String name;

    @JsonProperty("description")
    private String description = "";

}
