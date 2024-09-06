package com.myproject.media_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/1")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Ok");
	}

}
