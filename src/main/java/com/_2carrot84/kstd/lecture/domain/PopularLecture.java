package com._2carrot84.kstd.lecture.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PopularLecture {
	private Long lectureId;
	private Long count;
}
