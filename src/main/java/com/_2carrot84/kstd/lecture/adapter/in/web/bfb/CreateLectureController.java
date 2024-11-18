package com._2carrot84.kstd.lecture.adapter.in.web.bfb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2carrot84.kstd.common.dto.CommonResponse;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.CreateLectureRequest;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.LectureResponse;
import com._2carrot84.kstd.lecture.application.port.in.CreateLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.CreateLectureUseCase;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/bfb/lectures"})
@RequiredArgsConstructor
public class CreateLectureController {

	private final CreateLectureUseCase createLectureUseCase;

	@PostMapping
	public ResponseEntity<CommonResponse<LectureResponse>> create(@RequestBody CreateLectureRequest request) {
		return ResponseEntity.ok(
			CommonResponse.success(
				LectureMapper.mapToDto(createLectureUseCase.create(CreateLectureCommand.builder()
					.name(request.getName())
					.speaker(request.getSpeaker())
					.room(request.getRoom())
					.content(request.getContent())
					.applicantCount(request.getMaxApplicantCount())
					.startDateTime(request.getStartDateTime())
					.build()
				))
			)
		);
	}
}
