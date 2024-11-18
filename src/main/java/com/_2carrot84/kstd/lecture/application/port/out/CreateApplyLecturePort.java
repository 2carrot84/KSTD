package com._2carrot84.kstd.lecture.application.port.out;

import com._2carrot84.kstd.lecture.domain.ApplyLecture;

public interface CreateApplyLecturePort {
	ApplyLecture create(ApplyLecture applyLecture);
}
