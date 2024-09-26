package com.myproject.product_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.shared.PagingAndSortingResponse;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 7:23 AM Thu 9/26/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListProductResponse extends PagingAndSortingResponse {

    @JsonProperty("products")
    private List<ProductDTO> products;

}
