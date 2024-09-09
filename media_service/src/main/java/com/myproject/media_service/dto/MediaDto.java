package com.myproject.media_service.dto;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;

/**
 * @author nguyenle
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDto {

	private InputStream content;

	private MediaType mediaType;
}
