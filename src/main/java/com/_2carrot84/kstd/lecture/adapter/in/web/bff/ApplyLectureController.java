package com._2carrot84.kstd.lecture.adapter.in.web.bff;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.ApplyLectureRequest;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.ApplyLectureResponse;
import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureUseCase;
import com._2carrot84.kstd.lecture.domain.mapper.ApplyLectureMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bff/lectures"})
@RequiredArgsConstructor
public class ApplyLectureController {

	private final ApplyLectureUseCase applyLectureUseCase;

	@PostMapping("/apply")
	public ResponseEntity<CommonResponse<ApplyLectureResponse>> apply(
		@RequestBody ApplyLectureRequest request) {
		return ResponseEntity.ok(
			CommonResponse.success(
				ApplyLectureMapper.toDto(applyLectureUseCase.apply(ApplyLectureCommand.builder()
					.lectureId(request.getLectureId())
					.employeeId(request.getEmployeeId())
					.build()))
			)
		);
	}
}
