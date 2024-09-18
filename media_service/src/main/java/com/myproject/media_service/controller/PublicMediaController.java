package com.myproject.media_service.controller;

import com.myproject.media_service.dto.MediaDto;
import com.myproject.media_service.service.MediaService;
import com.myproject.media_service.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class PublicMediaController {

    private final MediaService mediaService;

    @GetMapping("/view/{hashId}")
    public ResponseEntity<InputStreamResource> getFile(
        @PathVariable(name = "hashId") String hashId
    ) {
        Long id = UrlUtils.getId(hashId);
        String fileName = UrlUtils.getFileName(hashId);
        MediaDto mediaDto = mediaService.getFileResource(id, fileName);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
            .contentType(mediaDto.getMediaType())
            .body(new InputStreamResource(mediaDto.getContent()));
    }

}
