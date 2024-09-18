package com.myproject.media_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nguyenle
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
