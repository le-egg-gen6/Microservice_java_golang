package com.myproject.product_service.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author nguyenle
 * @since 12:51 AM Fri 9/20/2024
 */

/*
* Note: please use @Valid while Request extend from PagingAndSortingRequest pass to controller
* */
@Getter
@Setter
public abstract class PagingAndSortingRequest {

    @JsonProperty("page")
    @Min(value = 0)
    private Integer page = 0;

    @JsonProperty("pageSize")
    @Min(value = 10)
    private Integer pageSize = 10;

    @JsonProperty("sortBy")
    private String sortBy = "id";

    @JsonProperty("sortDirection")
    private String sortDirection = "ASC";

    private Sort.Direction getSortDirection() {
        if (this.sortBy.equals("DSC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

    public PageRequest getPageRequest() {
        Sort sort = Sort.unsorted();
        if (!sortBy.isEmpty()) {
            sort = Sort.by(getSortDirection(), sortBy);
        }
        return PageRequest.of(page, pageSize, sort);
    }

}
