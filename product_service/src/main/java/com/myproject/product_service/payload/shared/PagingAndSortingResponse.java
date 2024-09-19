package com.myproject.product_service.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 1:05 AM Fri 9/20/2024
 */
@Getter
@Setter
public abstract class PagingAndSortingResponse {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("sortBy")
    private String sortBy;

    @JsonProperty("sortDirection")
    private String sortDirection;

}
