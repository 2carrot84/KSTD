package com._2carrot84.kstd.lecture.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com._2carrot84.kstd.lecture.application.port.in.FindApplyLectureUserCase;
import com._2carrot84.kstd.lecture.application.port.out.FindApplyLecturePort;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindApplyLectureService implements FindApplyLectureUserCase {

	private final FindApplyLecturePort findApplyLecturePort;

	@Override
	public List<ApplyLecture> findByEmployeeId(String employeeId) {
		return findApplyLecturePort.findByEmployeeId(employeeId);
	}
}
