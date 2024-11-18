package com._2carrot84.kstd.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static LocalDateTime parse(String dateTime) {
		return LocalDateTime.parse(dateTime, formatter);
	}

	public static String parse(LocalDateTime dateTime) {
		return dateTime.format(formatter);
	}
}
