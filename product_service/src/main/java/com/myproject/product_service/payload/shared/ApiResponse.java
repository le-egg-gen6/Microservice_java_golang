package com.myproject.product_service.payload.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/**
 * @author nguyenle
 * @since 7:39 AM Fri 9/13/2024
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
