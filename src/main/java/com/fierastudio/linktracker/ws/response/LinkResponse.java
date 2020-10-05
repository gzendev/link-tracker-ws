package com.fierastudio.linktracker.ws.response;

import lombok.Data;

@Data
public class LinkResponse {
	
	private String target;
	private String link;
	private boolean valid;

}