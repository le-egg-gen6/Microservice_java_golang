package com.myproject.product_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.shared.PagingAndSortingResponse;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 1:31 AM Fri 9/20/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryWithProductResponse extends PagingAndSortingResponse {

    @JsonProperty("category")
    private CategoryDTO category;

    @JsonProperty("products")
    private List<ProductDTO> products;

}
