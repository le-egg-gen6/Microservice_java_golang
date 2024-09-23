package com.myproject.notification_service.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myproject.notification_service.utils.CustomLocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 1:53 PM Mon 9/23/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

	@JsonProperty("id")
	private String id;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("type")
	private Integer type;

	@JsonProperty("title")
	private String title;

	@JsonProperty("content")
	private String content;

	@JsonProperty("read")
	private boolean read;

	@JsonProperty("createdAt")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

}
