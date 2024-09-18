package com.myproject.product_service.config.media_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nguyenle
 * @since 10:50 PM Sun 9/15/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaUploadRequest {

    @NotNull
    @JsonProperty("multipartFile")
    private MultipartFile multipartFile;

    @JsonProperty("fileName")
    private String fileName;

}
