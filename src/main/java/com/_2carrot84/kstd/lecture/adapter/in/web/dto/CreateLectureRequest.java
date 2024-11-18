package com._2carrot84.kstd.lecture.adapter.in.web.dto;

import lombok.Getter;

@Getter
public class CreateLectureRequest {
	private String name;
	private String speaker;
	private String room;
	private String content;
	private int maxApplicantCount;
	private String startDateTime;
}
