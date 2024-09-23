package com.myproject.notification_service.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myproject.notification_service.utils.CustomLocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 1:54 PM Mon 9/23/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationResponse {

	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("createdAt")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

	@JsonProperty("modifiedAt")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime modifiedAt;

	@JsonProperty("participantIds")
	private List<String> participantIds;

	@JsonProperty("lastMessage")
	private MessageResponse lastMessage;

}
