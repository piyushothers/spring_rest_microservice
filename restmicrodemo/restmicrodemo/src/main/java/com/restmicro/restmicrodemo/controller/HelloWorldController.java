package com.restmicro.restmicrodemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello World!!!";
	}
	
	@GetMapping(path = "hello-world/path-variable/{name1}/{name2}")
	public String helloWorldPathVariable(@PathVariable(name = "name")String x , @PathVariable(name = "name2")String y) {
		return String.format("Hello World %s and %s !!!",x,y);
	}
}
