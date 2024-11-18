package com._2carrot84.kstd.lecture.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lecture {
	private Long id;
	private String name;
	private LocalDateTime startDateTime;
	private String speaker;
	private String room;
	private int maxApplicantCount;
	private String content;
	@Builder.Default
	private List<ApplyLecture> applicants = new ArrayList<>();

	public boolean canApply() {
		return !isOverApplicant() && isBeforeStartDateTIme();
	}

	private boolean isBeforeStartDateTIme() {
		return startDateTime.isAfter(LocalDateTime.now());
	}

	private boolean isOverApplicant() {
		return maxApplicantCount <= applicants.size();
	}
}
