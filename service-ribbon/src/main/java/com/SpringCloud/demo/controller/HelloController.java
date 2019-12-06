package com.SpringCloud.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringCloud.demo.service.HelloService;

@RestController
public class HelloController {

	@Autowired
	HelloService helloService;
	
	@GetMapping(value="/hi")
	public String hi(HttpServletRequest request) {
		String name = (String)request.getParameter("name");
		 return helloService.hiService( name );
	}
	
	
}
