package com.fierastudio.linktracker.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fierastudio.linktracker.ws.dto.StatisticDto;
import com.fierastudio.linktracker.ws.services.LinkService;

@RestController
@RequestMapping("/statistic")
public class StatisticsContoller {
	
	@Autowired
	private LinkService linkService;
	
	@GetMapping("/{protocol}/{domain}/{subdomain}/{shortened}")
	public ResponseEntity<StatisticDto> getByShortened(@PathVariable final String protocol, @PathVariable final String domain, 
												@PathVariable final String subdomain, @PathVariable final String shortened) {
		return ResponseEntity.ok(linkService.getStatistic(protocol + "//" + domain + "/" + subdomain + "/" + shortened));
	}

}
