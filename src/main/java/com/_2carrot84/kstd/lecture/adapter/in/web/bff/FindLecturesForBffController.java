package com._2carrot84.kstd.lecture.adapter.in.web.bff;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.LectureResponse;
import com._2carrot84.kstd.lecture.application.port.in.FindLectureQuery;
import com._2carrot84.kstd.lecture.application.port.in.FindLecturesUseCase;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bff/lectures"})
@RequiredArgsConstructor
public class FindLecturesForBffController {

	private final FindLecturesUseCase findLecturesUseCase;

	@GetMapping
	public ResponseEntity<CommonResponse<List<LectureResponse>>> findAllBy(
		@RequestParam(required = false) String fromStartDateTime,
		@RequestParam(required = false) String toStartDateTime) {
		return ResponseEntity.ok(
			CommonResponse.success(
				findLecturesUseCase.findAllBy(FindLectureQuery.builder()
						.fromStartDateTime(fromStartDateTime)
						.toStartDateTime(toStartDateTime)
						.build())
					.stream()
					.map(LectureMapper::mapToDto)
					.toList()
			)
		);
	}
}
