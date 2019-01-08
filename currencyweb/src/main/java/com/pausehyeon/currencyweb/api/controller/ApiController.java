package com.pausehyeon.currencyweb.api.controller;

import java.text.MessageFormat;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.currencyweb.api.ApiException;
import com.pausehyeon.currencyweb.api.services.currency.CurrencyService;
import com.pausehyeon.currencyweb.api.vo.InquireRatesInVo;
import com.pausehyeon.currencyweb.api.vo.InquireRatesOutVo;
import com.pausehyeon.currencyweb.api.vo.base.CommonOutVo;

/**
 * @description API 컨트롤러
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	Environment env;	
	
	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping(method=RequestMethod.GET, path="/rates", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public InquireRatesOutVo getRates(InquireRatesInVo inVo) throws ApiException {
		InquireRatesOutVo outVo = new InquireRatesOutVo();
		
		try {
			LOGGER.debug("START");
			outVo = currencyService.inquireRates(inVo);
			outVo.setOk(true);
			LOGGER.debug("END");
		} catch (ApiException e) {
			LOGGER.info("", e.getStackTrace()[0]);
			outVo = (InquireRatesOutVo) setError(outVo, e.getErrorCode(), e.getMessageParams());
		} catch (Throwable e) {
			LOGGER.error("Uncaught Exception :: ", Arrays.toString(e.getStackTrace()));
			outVo = (InquireRatesOutVo) setError(outVo, "9001");
		}
		return outVo;
	}
	
	private CommonOutVo setError(CommonOutVo outVo, String errorCode, Object... messageParams) {
		outVo.setOk(false);
		if(errorCode == null) {
			outVo.setErrorCode("9001");
		}else {
			outVo.setErrorCode(errorCode);
		}
		String pattern = env.getProperty("error."+errorCode, "unknown error code");
		outVo.setErrorMessage(MessageFormat.format(pattern, messageParams));
		return outVo;
	};
}
