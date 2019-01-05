package com.pausehyeon.currencyweb.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/exchange")
public class ExchangeViewController {
	
	@RequestMapping(path="/calculate", method=RequestMethod.GET)
	public String calculator() {
		return "/exchange/calculate_01";
	}
}
