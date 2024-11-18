package com._2carrot84.kstd.lecture.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._2carrot84.kstd.lecture.application.port.in.CancelApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.CancelApplyLectureUseCase;
import com._2carrot84.kstd.lecture.application.port.out.DeleteApplyLecturePort;
import com._2carrot84.kstd.lecture.application.service.event.ApplyLectureEventPublisher;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancelApplyLectureService implements CancelApplyLectureUseCase {

	private final DeleteApplyLecturePort deleteApplyLecturePort;
	private final ApplyLectureEventPublisher publisher;

	@Override
	@Transactional
	public void cancel(CancelApplyLectureCommand command) {
		ApplyLecture applyLecture = ApplyLecture.builder()
			.employeeId(command.getEmployeeId())
			.lectureId(command.getLectureId())
			.build();

		deleteApplyLecturePort.delete(applyLecture);
		publisher.publishProductDeletedEvent(applyLecture);
	}
}
