package com.myproject.product_service.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @JsonProperty("totalPage")
    private Integer totalPage;

    @JsonProperty("sortBy")
    private String sortBy;

    @JsonProperty("sortDirection")
    private String sortDirection;

    public void initPageResponse(Pageable pageable) {
        this.page = pageable.getPageNumber(); // Spring Data JPA uses 0-based page numbers
        this.pageSize = pageable.getPageSize();

        Sort sort = pageable.getSort();
        if (sort.isSorted()) {
            Sort.Order order = sort.iterator().next();
            this.sortBy = order.getProperty();
            this.sortDirection = order.getDirection().name();
        } else {
            this.sortBy = null;
            this.sortDirection = null;
        }
    }
}
