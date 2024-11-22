package com.myproject.cart_service.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * @author nguyenle
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    public static ApiResponse<?> successResponse(Object data) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .result(data)
                .build();
    }

    public static ApiResponse<?> errorResponse(HttpStatus httpStatus, String message) {
        return ApiResponse.builder()
                .code(httpStatus.value())
                .message(message)
                .build();
    }
}
