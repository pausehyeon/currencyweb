package com.pausehyeon.currencyweb.api.services.currency;

import com.pausehyeon.currencyweb.api.ApiException;
import com.pausehyeon.currencyweb.api.vo.InquireRatesInVo;
import com.pausehyeon.currencyweb.api.vo.InquireRatesOutVo;

public interface CurrencyService {
	public InquireRatesOutVo inquireRates(InquireRatesInVo inVo) throws ApiException;
}
