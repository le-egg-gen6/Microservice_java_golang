package com.myproject.product_service.controller.internal;

import com.myproject.product_service.entity.Product;
import com.myproject.product_service.payload.request.CalculateCartPriceRequest;
import com.myproject.product_service.payload.request.ValidateCartRequest;
import com.myproject.product_service.payload.response.CalculateCartPriceResponse;
import com.myproject.product_service.payload.response.ValidateCartResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author nguyenle
 * @since 11:07 AM Wed 9/25/2024
 */
@RestController
@RequestMapping("/internal/product")
@RequiredArgsConstructor
public class InternalProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<ApiResponse<?>> testController() {
		return ResponseEntity.ok(ApiResponse.successResponse("Ok!"));
	}

	@PostMapping("/validate")
	public ResponseEntity<ApiResponse<?>> validateCart(
			@RequestBody ValidateCartRequest request
	) {
		ValidateCartResponse validateCartResponse = ValidateCartResponse.builder()
				.success(true)
				.build();
		for (Map.Entry<Long, Integer> pair : request.getProducts().entrySet()) {
			Product product = productService.getProduct(pair.getKey());
			if (product == null || product.getStockQuantity() < pair.getValue()) {
				validateCartResponse.setSuccess(false);
				validateCartResponse.getInvalidProducts().put(
						pair.getKey(),
						product == null ? 0 : product.getStockQuantity()
				);
			}
		}
		return ResponseEntity.ok(ApiResponse.successResponse(validateCartResponse));
	}

	@PostMapping("/calulate")
	public ResponseEntity<ApiResponse<?>> calculateCartPrice(
			@RequestBody CalculateCartPriceRequest request
	) {
		double price = 0d;
		for (Map.Entry<Long, Integer> pair : request.getProducts().entrySet()) {
			price += productService.calculateProductPrice(pair.getKey()) * pair.getValue();
		}
		CalculateCartPriceResponse response = CalculateCartPriceResponse.builder()
				.price(price)
				.build();
		return ResponseEntity.ok(ApiResponse.successResponse(price));
	}
}
