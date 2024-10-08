package com.myproject.media_service.controller;

import com.myproject.media_service.payload.ApiResponse;
import com.myproject.media_service.payload.request.MediaUploadRequest;
import com.myproject.media_service.payload.response.MediaResponse;
import com.myproject.media_service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 4:30 PM Wed 9/18/2024
 */
@RestController
@RequestMapping("/internal/media")
@RequiredArgsConstructor
public class PrivateMediaController {

	private final MediaService mediaService;

	@GetMapping("/info/{id}")
	public ResponseEntity<ApiResponse<?>> get(@PathVariable(name = "id") Long id) {
		MediaResponse mediaResponse = mediaService.getMediaById(id);
		return ResponseEntity.ok().body(
			ApiResponse.builder()
				.code(HttpStatus.OK.value())
				.result(mediaResponse)
				.build()
		);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<?>> create(@RequestBody MediaUploadRequest request) {
		MediaResponse response = mediaService.saveMedia(request);
		return ResponseEntity.ok().body(
			ApiResponse.builder()
				.code(HttpStatus.OK.value())
				.result(response)
				.build()
		);
	}

}
