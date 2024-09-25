package com.myproject.product_service.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

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
    private Integer page = 0;

    @JsonProperty("pageSize")
    private Integer pageSize = 10;

    @JsonProperty("sortBy")
    private String sortBy = "id";

    @JsonProperty("sortDirection")
    private String sortDirection = "ASC";

    private Sort.Direction getSortDirection() {
        if (this.sortBy.equals("DESC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

    public PageRequest getPageRequest(Class<?> clazz) {
        Sort sort = Sort.unsorted();
        if (!sortBy.isEmpty()) {
            boolean isSortFieldCorrect = false;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equals(sortBy)) {
                    sortBy = field.getName();
                    isSortFieldCorrect = true;
                    break;
                }
            }
            if (!isSortFieldCorrect) {
                sortBy = "id";
            }
            sort = Sort.by(getSortDirection(), sortBy);
        }
        if (page < 0) {
            page = 0;
        }
        if (pageSize < 10) {
            pageSize = 10;
        }
        return PageRequest.of(page, pageSize, sort);
    }

}
