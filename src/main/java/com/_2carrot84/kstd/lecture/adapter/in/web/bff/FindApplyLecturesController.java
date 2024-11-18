package com._2carrot84.kstd.lecture.adapter.in.web.bff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.ApplyLecturesResponse;
import com._2carrot84.kstd.lecture.application.port.in.FindApplyLectureUserCase;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bff/lectures"})
@RequiredArgsConstructor
public class FindApplyLecturesController {

	private final FindApplyLectureUserCase findApplyLectureUserCase;

	@GetMapping("/{employeeId}/apply")
	public ResponseEntity<CommonResponse<ApplyLecturesResponse>> findBy(@PathVariable String employeeId) {
		List<ApplyLecturesResponse.ApplyLectureWithLecture> applyLectures = new ArrayList<>();

		findApplyLectureUserCase.findByEmployeeId(employeeId)
			.forEach(applyLecture -> applyLectures.add(ApplyLecturesResponse.ApplyLectureWithLecture.builder()
				.lecture(LectureMapper.mapToDto(applyLecture.getLecture()))
				.applyDateTime(applyLecture.getApplyDateTime())
				.employeeId(applyLecture.getEmployeeId())
				.build()));

		return ResponseEntity.ok(
			CommonResponse.success(
				ApplyLecturesResponse.builder()
					.applyLectures(applyLectures)
					.build()
			)
		);
	}
}
