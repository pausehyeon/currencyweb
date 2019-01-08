package com.pausehyeon.currencyweb.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonViewController {
	Logger LOGGER = LoggerFactory.getLogger(CommonViewController.class);
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public String index() {
		return "index";
	}
}
