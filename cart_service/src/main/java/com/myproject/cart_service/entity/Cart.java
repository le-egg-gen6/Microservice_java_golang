package com.myproject.cart_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

/**
 * @author nguyenle
 */
@Document(collection = "c_cart")
@CompoundIndexes({
        @CompoundIndex(def = "{'state': -1, 'created_at': -1}", background = true)
})
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    private String id;

    @Indexed(background = true)
    @Field("user_id")
    private String userId;

    @Field("price")
    private Double price;

    @Field("products")
    private Map<Long, Integer> products = new HashMap<>();

    @Field("created_at")
    private Date createdAt = new Date();

    @Indexed(background = true)
    @Field("state")
    private int state = CartState.UNCONFIRMED.getValue();

    @Field("location_id")
    private String locationId;

    @Field("additional_details")
    private List<String> additionalDetails = new ArrayList<>();

    @Field("fail_reasons")
    private List<String> failReasons = new ArrayList<>();

    public boolean isCartModifiable() {
        return state == CartState.UNCONFIRMED.getValue();
    }

}
