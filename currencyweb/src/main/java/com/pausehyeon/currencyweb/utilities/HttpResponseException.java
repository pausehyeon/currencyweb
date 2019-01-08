package com.pausehyeon.currencyweb.utilities;

/**
 * @description Http 통신 결과가 정상이 아닌 경우 발생하는 예외
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
public class HttpResponseException extends Exception {

	private static final long serialVersionUID = 3619987565601444676L;
	
	private String responseCode = "";
	
	public HttpResponseException(String responseCode) {
		super("HTTP RESPONSE CODE :: " + responseCode);
		this.responseCode = responseCode;
	}
	
	public String getResponseCode() {
		return this.responseCode;
	}
}
