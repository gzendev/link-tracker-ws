package com.fierastudio.linktracker.ws.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fierastudio.linktracker.ws.services.RedirectService;

@RestController
public class RedirectController {
	
	@Autowired
	private RedirectService redirectService;
	
	@GetMapping("/{subdomain}/{shortened}")
	public void redirect(HttpServletResponse response, @PathVariable(required = true) final String subdomain,
						@PathVariable(required = true) final String shortened,
						@Valid @RequestParam(name = "token", required = true) final String token) {
    	try {
    		String ret = redirectService.getUrl(subdomain, shortened, token);
    		response.sendRedirect(ret);
		} catch (Exception e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
}
