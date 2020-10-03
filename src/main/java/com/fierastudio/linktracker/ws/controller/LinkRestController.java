package com.fierastudio.linktracker.ws.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fierastudio.linktracker.ws.message.ResponseMessage;

@RestController
public class LinkRestController {
	
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    @PostMapping
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody(required = true) final String url) {
	  return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(""));
	}

    @RequestMapping("/l/{mask}")
	public void redirect(HttpServletResponse response, @PathVariable final String mask) throws IOException {
    	response.sendRedirect("unmasked");
	}
    
    @GetMapping("/l/{mask}")
	public ResponseEntity<ResponseMessage> statistic(@PathVariable final String mask) {
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(""));
	}
    
    @PutMapping("/l/{mask}")
    public ResponseEntity<ResponseMessage> invalidate(@PathVariable final String mask) {
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(""));
    }
    
}
