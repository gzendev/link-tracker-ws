package com.fierastudio.linktracker.ws.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fierastudio.linktracker.ws.dto.LinkDto;
import com.fierastudio.linktracker.ws.dto.LinkRequest;
import com.fierastudio.linktracker.ws.services.LinkService;

@RestController
@RequestMapping("/link")
public class LinkRestController {
	
    @Autowired
    private LinkService linkService;
    
    @PostMapping("/create")
	public ResponseEntity<LinkDto> create(@Valid @RequestBody(required = true) final LinkRequest request,
										@Valid @RequestHeader(required = true) final String token) throws ParseException {
      DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
	  return ResponseEntity.ok(linkService.save(request.getTarget(), format.parse(request.getExpiration()), token));
	}
}
