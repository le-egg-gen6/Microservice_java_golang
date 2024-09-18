package com.myproject.media_service.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author nguyenle
 */
@Entity
@Table(
        name = "t_media",
        indexes = {
                @Index(name = "t_media_idx_1", columnList = "fileName"),
                @Index(name = "t_media_idx_2", columnList = "caption")
        }
)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] data;

    private String mediaType;

}
