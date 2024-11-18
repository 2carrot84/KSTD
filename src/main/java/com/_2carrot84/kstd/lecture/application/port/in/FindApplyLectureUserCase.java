package com._2carrot84.kstd.lecture.application.port.in;

import java.util.List;

import com._2carrot84.kstd.lecture.domain.ApplyLecture;

public interface FindApplyLectureUserCase {
	List<ApplyLecture> findByEmployeeId(String employeeId);
}
