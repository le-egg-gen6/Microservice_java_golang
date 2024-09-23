package com.myproject.notification_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author nguyenle
 * @since 3:12 PM Sat 9/21/2024
 */
@Document(
        collection = "c_notification"
)
@CompoundIndexes({
        @CompoundIndex(def = "{'user_id' : 1, 'is_read' : 1, 'created_at' : -1}", background = true)
})
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    private String id;

    @Indexed(background = true)
    @Field("user_id")
    private String userId;

    @Field("type")
    private Integer type;

    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @Field("is_read")
    private boolean read;

    @Field("created_at")
    private LocalDateTime createdAt;
}
