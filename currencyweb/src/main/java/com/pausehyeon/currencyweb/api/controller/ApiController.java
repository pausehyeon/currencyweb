package com.pausehyeon.currencyweb.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
	
	@RequestMapping(method=RequestMethod.GET, path="/rates", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getRates() {
		LOGGER.debug("entered");
		return "{'result':'ok'}";
	}
}
