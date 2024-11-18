package com._2carrot84.kstd.lecture.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.port.out.CreateApplyLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.FindLecturesPort;
import com._2carrot84.kstd.lecture.application.service.event.ApplyLectureEventPublisher;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.Lecture;

@ExtendWith(MockitoExtension.class)
class ApplyLectureServiceTest {

	@Mock
	private CreateApplyLecturePort createApplyLecturePort;
	@Mock
	private FindLecturesPort findLecturesPort;
	@Mock
	private ApplyLectureEventPublisher publisher;
	@InjectMocks
	private ApplyLectureService applyLectureService;

	@Test
	void apply_fail_when_canApply() {
		ApplyLectureCommand command = ApplyLectureCommand.builder()
			.lectureId(1L)
			.employeeId("12345")
			.build();

		List<Lecture> lectures = List.of(Lecture.builder()
			.id(1L)
			.maxApplicantCount(1)
			.startDateTime(LocalDateTime.now().plusDays(1))
			.applicants(List.of(ApplyLecture.builder().build()))
			.build()
		);

		when(findLecturesPort.findByIdWithApplicants(command.getLectureId())).thenReturn(lectures);

		assertThatThrownBy(() -> applyLectureService.apply(command)).isInstanceOf(IllegalStateException.class);
	}

	@Test
	void apply_success() {
		ApplyLectureCommand command = ApplyLectureCommand.builder()
			.lectureId(1L)
			.employeeId("12345")
			.build();

		List<Lecture> lectures = List.of(Lecture.builder()
			.id(1L)
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now().plusDays(1))
			.applicants(List.of(ApplyLecture.builder().build()))
			.build()
		);

		when(findLecturesPort.findByIdWithApplicants(command.getLectureId())).thenReturn(lectures);

		ApplyLecture expected = ApplyLecture.builder()
			.lectureId(command.getLectureId())
			.employeeId(command.getEmployeeId())
			.build();

		when(createApplyLecturePort.create(any())).thenReturn(expected);

		ApplyLecture applied = applyLectureService.apply(command);

		assertThat(applied).isEqualTo(expected);
	}
}