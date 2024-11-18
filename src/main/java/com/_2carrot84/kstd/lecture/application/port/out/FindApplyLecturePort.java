package com._2carrot84.kstd.lecture.application.port.out;

import java.util.List;

import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

public interface FindApplyLecturePort {
	List<ApplyLecture> findByEmployeeId(String employeeId);
	List<PopularLecture> findPopularLectures();
}
