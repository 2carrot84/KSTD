package com._2carrot84.kstd.lecture.application.port.in;

import com._2carrot84.kstd.common.validation.SelfValidating;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindLectureQuery extends SelfValidating<FindLectureQuery> {
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "날짜 타입(yyyy-MM-dd HH:mm)에 맞춰야 합니다.")
	private final String fromStartDateTime;

	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "날짜 타입(yyyy-MM-dd HH:mm)에 맞춰야 합니다.")
	private final String toStartDateTime;

	@Builder
	private FindLectureQuery(String fromStartDateTime, String toStartDateTime) {
		this.fromStartDateTime = fromStartDateTime;
		this.toStartDateTime = toStartDateTime;
		this.validateSelf();
	}
}
