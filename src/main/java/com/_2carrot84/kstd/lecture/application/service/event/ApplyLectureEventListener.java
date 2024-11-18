package com._2carrot84.kstd.lecture.application.service.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com._2carrot84.kstd.lecture.application.port.out.DecreaseApplicantCountPort;
import com._2carrot84.kstd.lecture.application.port.out.IncreaseApplicantCountPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplyLectureEventListener {
	private final IncreaseApplicantCountPort increaseApplicantCountPort;
	private final DecreaseApplicantCountPort decreaseApplicantCountPort;

	@Async("taskExecutor")
	@EventListener
	public void increaseApplicantCountPort(LectureAppliedEvent event) {
		increaseApplicantCountPort.increaseApplicantCount(event.applyLecture().getLectureId());
	}

	@Async("taskExecutor")
	@EventListener
	public void decreaseApplicantCountPort(ApplyLectureCanceledEvent event) {
		decreaseApplicantCountPort.decreaseApplicantCount(event.applyLecture().getLectureId());
	}
}
