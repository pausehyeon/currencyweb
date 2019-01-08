package com.pausehyeon.currencyweb.api.vo;

import com.pausehyeon.currencyweb.api.vo.base.CommonOutVo;

public class InquireRatesOutVo extends CommonOutVo {
	private Double rate;
	private String datetime;

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "InquireRatesOutVo [rate=" + rate + ", datetime=" + datetime + "]";
	}

}
