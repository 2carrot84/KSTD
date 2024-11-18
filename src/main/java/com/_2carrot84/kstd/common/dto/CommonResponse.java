package com._2carrot84.kstd.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {
	private boolean success;
	private String message;
	private T data;

	public static <T> CommonResponse<T> success(T data) {
		return new CommonResponse<>(true, "요청이 성공적으로 처리되었습니다.", data);
	}

	public static <T> CommonResponse<T> fail(String message) {
		return new CommonResponse<>(false, message, null);
	}
}
