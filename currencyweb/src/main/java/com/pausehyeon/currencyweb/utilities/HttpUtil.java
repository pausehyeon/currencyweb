package com.pausehyeon.currencyweb.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @description 아웃바운드 통신을 위한 유틸리티
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
public class HttpUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * @description GET 방식 호출
	 * @param targetUrl
	 * @param data
	 * @param requestProperties
	 * @throws IOException
	 * @return void
	 * @throws HttpResponseException
	 */
	public static String sendGET(String targetUrl, MultiValueMap<String, String> data, Map<String, String> requestProperties) throws IOException, HttpResponseException {
		StringBuffer response = new StringBuffer();
		URL obj = null;
		
		// 쿼리 파라미터 세팅 
		if(data != null) {
			UriComponents uc = UriComponentsBuilder.fromHttpUrl(targetUrl).queryParams(data).build();
			obj = uc.toUri().toURL();
			LOGGER.debug("Call {} - Query Params ::{} ", targetUrl, uc.toUriString());
		}else {
			obj = new URL(targetUrl);
		}
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		if(requestProperties != null) {
			for (String key : requestProperties.keySet()) {
				con.setRequestProperty(key, requestProperties.get(key));
				LOGGER.debug("Call {} - Request Property :: {} = {}", targetUrl, key, requestProperties.get(key));
			}
		}
		int responseCode = con.getResponseCode();
		LOGGER.info("Call {} - Response Code :: {}", targetUrl, responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			LOGGER.debug(new String(response));
		} else {
			throw new HttpResponseException("" + responseCode);
		}
		
		return new String(response);
	}
}
