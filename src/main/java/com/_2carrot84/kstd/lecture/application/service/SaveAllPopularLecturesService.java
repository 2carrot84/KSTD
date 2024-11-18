package com._2carrot84.kstd.lecture.application.service;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._2carrot84.kstd.lecture.application.port.out.DeleteAllPopularLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.FindApplyLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.SavePopularLecturePort;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@Profile("!test")
@RequiredArgsConstructor
public class SaveAllPopularLecturesService {
	private final FindApplyLecturePort findApplyLecturePort;
	private final SavePopularLecturePort savePopularLecturePort;
	private final DeleteAllPopularLecturePort deleteAllPopularLecturePort;

	@PostConstruct
	@Transactional
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void saveAllPopularLectures() {
		deleteAllPopularLecturePort.deleteAll();

		findApplyLecturePort.findPopularLectures()
			.forEach(savePopularLecturePort::save);
	}
}
