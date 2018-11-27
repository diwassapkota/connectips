package com.nchl.connectips.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/*")
public class ApiController {

	@PostMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
