package com._2carrot84.kstd.lecture.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._2carrot84.kstd.common.annotation.DistributedLock;
import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureUseCase;
import com._2carrot84.kstd.lecture.application.port.out.CreateApplyLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.FindLecturesPort;
import com._2carrot84.kstd.lecture.application.service.event.ApplyLectureEventPublisher;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.Lecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyLectureService implements ApplyLectureUseCase {

	private final CreateApplyLecturePort createApplyLecturePort;
	private final FindLecturesPort findLecturesPort;
	private final ApplyLectureEventPublisher publisher;

	@Override
	@DistributedLock(key = "#command.lectureId")
	public ApplyLecture apply(ApplyLectureCommand command) {
		Lecture lecture = findLecturesPort.findByIdWithApplicants(command.getLectureId()).get(0);

		if (!lecture.canApply()) {
			throw new IllegalStateException("신청이 불가한 강의입니다.");
		}

		ApplyLecture applyLecture = createApplyLecturePort.create(ApplyLecture.builder()
			.lectureId(lecture.getId())
			.employeeId(command.getEmployeeId())
			.build());

		publisher.publishProductCreateEvent(applyLecture);

		return applyLecture;
	}
}
