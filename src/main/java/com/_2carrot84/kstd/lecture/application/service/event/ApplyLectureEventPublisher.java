package com._2carrot84.kstd.lecture.application.service.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com._2carrot84.kstd.lecture.domain.ApplyLecture;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplyLectureEventPublisher {
	private final ApplicationEventPublisher publisher;

	public void publishProductCreateEvent(ApplyLecture applyLecture) {
		publisher.publishEvent(new LectureAppliedEvent(applyLecture));
	}

	public void publishProductDeletedEvent(ApplyLecture applyLecture) {
		publisher.publishEvent(new ApplyLectureCanceledEvent(applyLecture));
	}
}
