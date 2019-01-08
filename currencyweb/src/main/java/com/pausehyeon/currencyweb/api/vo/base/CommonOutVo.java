package com.pausehyeon.currencyweb.api.vo.base;

/**
 * @descriptio 표준 응답VO
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
public class CommonOutVo {
	private boolean ok;
	private String errorCode;
	private String errorMessage;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "CommonOutVo [ok=" + ok + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

}
