package com.myproject.media_service.controller;

import com.myproject.media_service.dto.MediaDto;
import com.myproject.media_service.payload.ApiResponse;
import com.myproject.media_service.payload.request.MediaUploadRequest;
import com.myproject.media_service.payload.response.MediaResponse;
import com.myproject.media_service.payload.response.NoMediaResponse;
import com.myproject.media_service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
 */
@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

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
        NoMediaResponse response = mediaService.saveMedia(request);
        return ResponseEntity.ok().body(
            ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .result(response)
                .build()
        );
    }

    @GetMapping("/view/{id}/file/{fileName}")
    public ResponseEntity<InputStreamResource> getFile(
        @PathVariable(name = "id") Long id,
        @PathVariable(name = "fileName") String fileName
    ) {
        MediaDto mediaDto = mediaService.getFileResource(id, fileName);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
            .contentType(mediaDto.getMediaType())
            .body(new InputStreamResource(mediaDto.getContent()));
    }

}
