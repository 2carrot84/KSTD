package com._2carrot84.kstd.lecture.adapter.in.web.bff;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.application.port.in.CancelApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.CancelApplyLectureUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bff/lectures"})
@RequiredArgsConstructor
public class CancelApplyLectureController {

	private final CancelApplyLectureUseCase cancelApplyLectureUseCase;

	@DeleteMapping(value = {"/{lectureId}/{employeeId}/apply"})
	public ResponseEntity<CommonResponse> cancel(
		@PathVariable Long lectureId, @PathVariable String employeeId) {

		cancelApplyLectureUseCase.cancel(CancelApplyLectureCommand.builder()
			.lectureId(lectureId)
			.employeeId(employeeId)
			.build());

		return ResponseEntity.ok(CommonResponse.success(null));
	}
}
