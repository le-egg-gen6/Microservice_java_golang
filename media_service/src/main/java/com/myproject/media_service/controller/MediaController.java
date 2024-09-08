package com.myproject.media_service.controller;

import com.myproject.media_service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;



}
