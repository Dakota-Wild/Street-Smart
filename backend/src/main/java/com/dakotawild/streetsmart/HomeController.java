package com.dakotawild.streetsmart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping
@RestController
public class HomeController {
	@GetMapping("/home")
	public String home() {
		return "Dakota Wild Home Page";
	}

	@GetMapping("/Andrew_URL")
	public String getURLandrew() {
		return "Lakers 2020 NBA Champions confirmed :P";
	}

	@GetMapping("/Kobe_URL")
	public String getURLKobe() {
		return "Week 5 yet done with school";
	}

	@GetMapping("/Darrell_URL")
	public String getURLDarrell() {
		return "im still so confused";
	}

	@GetMapping("/Dakota_URL")
	public String getURLDakota() {
		return "This is whack dot com";
	}

	@GetMapping("/Celine_URL")
	public String getURLCeline() {
		return "What is school?";
	}
}
