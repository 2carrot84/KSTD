package com._2carrot84.kstd.lecture.application.port.in;

import com._2carrot84.kstd.lecture.domain.ApplyLecture;

public interface ApplyLectureUseCase {
	ApplyLecture apply(ApplyLectureCommand command);
}