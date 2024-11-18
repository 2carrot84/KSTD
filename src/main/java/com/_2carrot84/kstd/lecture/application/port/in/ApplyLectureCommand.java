package com._2carrot84.kstd.lecture.application.port.in;

import com._2carrot84.kstd.common.validation.SelfValidating;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyLectureCommand extends SelfValidating<ApplyLectureCommand> {
	@NotNull
	@Size(min = 5, max = 5)
	private final String employeeId;
	@NotNull
	private final Long lectureId;

	@Builder
	public ApplyLectureCommand(String employeeId, Long lectureId) {
		this.employeeId = employeeId;
		this.lectureId = lectureId;
		this.validateSelf();
	}
}
