package com.pausehyeon.currencyweb.api;

/* @description 
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
public class ApiException extends Exception {
	private static final long serialVersionUID = -7556342491905209671L;
	
	String errorCode;
	String errorMessage;
	Object[] messageParams;
	
	public ApiException(String errorCode) {
		super("API ERROR CODE :: " + errorCode);
		this.errorCode = errorCode;
	}
	
	public ApiException(String errorCode, Object... messageParams) {
		super("API ERROR CODE :: " + errorCode);
		this.errorCode = errorCode;
		this.messageParams = messageParams;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}
	
}
