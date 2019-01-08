package com.pausehyeon.currencyweb.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 입출력 형식 관련 유틸리티
 * @author pausehyeon@gmail.com
 * @created 2019. 1. 6.
 */
public class FormatUtil {

	/**
	 * @description 타임스탬프를 일시 문자열로 치환
	 * @param timestamp
	 * @return
	 * @return String
	 */
	public static String formatDateTime(long timestamp) {
		String datetime = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetime = formatter.format(new Date(timestamp*1000));
		return datetime;
	}
}
