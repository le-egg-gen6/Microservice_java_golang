package com.myproject.media_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("mediaType")
    private String mediaType;

    @JsonProperty("url")
    private String url;

}
