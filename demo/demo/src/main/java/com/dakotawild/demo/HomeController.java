package com.dakotawild.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HomeController {
	
	@GetMapping("/Andrew_URL")
	public String getURLandrew() {
		return "Lakers 2020 NBA Champions confirmed :P";
	}

	
	
}