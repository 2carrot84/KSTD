package com._2carrot84.kstd.lecture.adapter.in.web.bfb;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.LectureResponse;
import com._2carrot84.kstd.lecture.application.port.in.FindLecturesUseCase;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bfb/lectures"})
@RequiredArgsConstructor
public class FindLecturesForBfbController {

	private final FindLecturesUseCase findLecturesUseCase;

	@GetMapping
	public ResponseEntity<CommonResponse<List<LectureResponse>>> findAll() {
		return ResponseEntity.ok(
			CommonResponse.success(
				findLecturesUseCase.findAll()
					.stream()
					.map(LectureMapper::mapToDto)
					.toList()
			)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponse<List<LectureResponse>>> findById(
		@PathVariable Long id) {
		return ResponseEntity.ok(
			CommonResponse.success(
				findLecturesUseCase.findByIdWithApplicants(id)
					.stream()
					.map(LectureMapper::mapToDtoWithApplicants)
					.toList()
			)
		);
	}
}
