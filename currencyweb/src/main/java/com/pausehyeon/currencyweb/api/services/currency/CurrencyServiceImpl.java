package com.pausehyeon.currencyweb.api.services.currency;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pausehyeon.currencyweb.api.ApiException;
import com.pausehyeon.currencyweb.api.vo.InquireRatesInVo;
import com.pausehyeon.currencyweb.api.vo.InquireRatesOutVo;
import com.pausehyeon.currencyweb.utilities.FormatUtil;
import com.pausehyeon.currencyweb.utilities.HttpResponseException;
import com.pausehyeon.currencyweb.utilities.HttpUtil;

@Component
public class CurrencyServiceImpl implements CurrencyService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	

	@Value("${url.currencylayer.live:}")
	private String targetUrl;
	@Value("${currencylayer.key:}")
	private String accessKey;
	
	@SuppressWarnings("unchecked")
	public InquireRatesOutVo inquireRates(InquireRatesInVo inVo) throws ApiException {
		InquireRatesOutVo outVo = new InquireRatesOutVo();
		if(inVo.getSource() == null || "".equals(inVo.getSource().trim())) {
			throw new ApiException("1001", "source");
		}
		
		if(inVo.getTarget() == null || "".equals(inVo.getTarget().trim())) {
			throw new ApiException("1001", "target");
		}
		
		
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
		data.add("access_key", accessKey);
		data.add("source", inVo.getSource());
		data.add("format", "1");
		data.add("currencies", inVo.getTarget());
		
		String result = "";
		try {
			result = HttpUtil.sendGET(targetUrl, data, null);
		} catch (IOException e) {
			LOGGER.error("EXCEPTION OCCURED",e);
		} catch (HttpResponseException e) {
			LOGGER.error("EXCEPTION OCCURED",e);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap = mapper.readValue(result, resultMap.getClass());
			if((boolean)resultMap.get("success") == true) {
				Map<String, Double> currencyMap = (Map<String, Double>)resultMap.get("quotes");
				Double rate = currencyMap.get(inVo.getSource()+inVo.getTarget());
				outVo.setRate(rate);
				outVo.setDatetime(FormatUtil.formatDateTime((Integer)resultMap.get("timestamp")));
				LOGGER.debug("SUCCEED!!! {}", outVo.toString());
			}else {
				LOGGER.debug("FAILED!!!");
			}
		} catch (IOException e) {
			LOGGER.error("EXCEPTION OCCURED",e);
			LOGGER.debug("result={}", result);
		}
		
		return outVo;
	}
	
}
