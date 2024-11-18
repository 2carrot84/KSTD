package com._2carrot84.kstd.lecture.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureResponse {
	private Long id;
	private String name;
	private String speaker;
	private String room;
	private String content;
	private int maxApplicantCount;
	private LocalDateTime startDateTime;
	private List<ApplyLectureResponse> applicants;
}
