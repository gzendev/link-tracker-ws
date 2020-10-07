package com.fierastudio.linktracker.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDto {
	
	private String target;
	private String link;
	private boolean valid;
	
}