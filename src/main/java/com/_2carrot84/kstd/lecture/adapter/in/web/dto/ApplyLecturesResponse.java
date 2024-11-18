package com._2carrot84.kstd.lecture.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyLecturesResponse {

	@Getter
	@Builder
	public static class ApplyLectureWithLecture {
		private LectureResponse lecture;
		private String employeeId;
		private LocalDateTime applyDateTime;
	}

	private List<ApplyLectureWithLecture> applyLectures;
}
