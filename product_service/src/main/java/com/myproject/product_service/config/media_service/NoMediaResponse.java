package com.myproject.product_service.config.media_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 10:53 PM Sun 9/15/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoMediaResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("mediaType")
    private String mediaType;

}
