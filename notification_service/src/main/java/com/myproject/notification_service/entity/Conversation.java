package com.myproject.notification_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nguyenle
 * @since 3:12 PM Sat 9/21/2024
 */
@Document(
        collection = "c_conversation"
)
@CompoundIndexes({
        @CompoundIndex(def = "{'participant_ids': 1, 'created_at': -1}", background = true),
        @CompoundIndex(def = "{'participant_ids': 1, 'modified_at': -1}", background = true)
})
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Indexed(background = true)
    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("modified_at")
    private LocalDateTime modifiedAt;

    @Indexed(background = true)
    @Field("participant_ids")
    private List<String> participantIds = new ArrayList<>();

    @Field("last_message_id")
    private String lastMessageId;


}
