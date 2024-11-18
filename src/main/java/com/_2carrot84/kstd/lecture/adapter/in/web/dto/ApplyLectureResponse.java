package com._2carrot84.kstd.lecture.adapter.in.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyLectureResponse {
	private Long lectureId;
	private String employeeId;
	private LocalDateTime applyDateTime;
}

