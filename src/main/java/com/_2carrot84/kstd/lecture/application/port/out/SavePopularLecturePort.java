package com._2carrot84.kstd.lecture.application.port.out;

import com._2carrot84.kstd.lecture.domain.PopularLecture;

public interface SavePopularLecturePort {
	void save(PopularLecture lecture);
}
