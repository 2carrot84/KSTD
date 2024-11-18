package com._2carrot84.kstd.lecture.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyLecture {
	private Long lectureId;
	private String employeeId;
	private LocalDateTime applyDateTime;
	private Lecture lecture;
}

