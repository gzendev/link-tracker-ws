package com.fierastudio.linktracker.ws.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fierastudio.linktracker.ws.services.RedirectService;

@RestController
@RequestMapping("/redirect")
public class RedirectController {
	
	@Autowired
	private RedirectService redirectService;
	
	@GetMapping("{shortened}")
	public void redirect(HttpServletResponse response, @PathVariable(required = true) final String shortened, 
						@Valid @RequestHeader(required = true) final String token) {
    	try {
    		response.setHeader("Location", redirectService.getUrl(shortened, token));
			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
}
