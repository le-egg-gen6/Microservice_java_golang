package com.myproject.product_service.controller.internal;

import com.myproject.product_service.payload.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 11:07 AM Wed 9/25/2024
 */
@RestController
@RequestMapping("/internal/product")
@RequiredArgsConstructor
public class InternalProductController {

	@GetMapping
	public ResponseEntity<ApiResponse<?>> testController() {
		return ResponseEntity.ok(ApiResponse.successResponse("Ok!"));
	}

}
