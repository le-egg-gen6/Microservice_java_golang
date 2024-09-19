package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 12:26 AM Fri 9/20/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @JsonProperty("name")
    @NonNull
    private String name;

    @JsonProperty("description")
    private String description;
}
