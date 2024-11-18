package com._2carrot84.kstd.lecture.application.port.in;

import com._2carrot84.kstd.lecture.domain.Lecture;

public interface CreateLectureUseCase {
	Lecture create(CreateLectureCommand command);
}
