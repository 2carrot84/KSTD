package com._2carrot84.kstd.lecture.application.port.out;

import com._2carrot84.kstd.lecture.domain.Lecture;

public interface CreateLecturesPort {
	Lecture create(Lecture lecture);
}
