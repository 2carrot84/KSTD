package com._2carrot84.kstd.lecture.adapter.in.web.dto;

import lombok.Getter;

@Getter
public class FindLectureRequest {
	private String fromStartDateTime;
	private String toStartDateTime;
}
