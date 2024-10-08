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

/**
 * @author nguyenle
 * @since 3:11 PM Sat 9/21/2024
 */
@Document(collection = "c_message")
@CompoundIndexes({
        @CompoundIndex(def = "{'conversation_id': 1, 'created_at': -1}", background = true)
})
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    private String id;

    @Indexed(background = true)
    @Field("conversation_id")
    private String conversationId;

    @Indexed(background = true)
    @Field("sender_id")
    private String senderId;

    @Field("content")
    private String content;

    @Indexed(background = true)
    @Field("created_at")
    private LocalDateTime createdAt;

}
