package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com._2carrot84.kstd.lecture.application.port.out.DecreaseApplicantCountPort;
import com._2carrot84.kstd.lecture.application.port.out.DeleteAllPopularLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.FindPopularLecturesPort;
import com._2carrot84.kstd.lecture.application.port.out.IncreaseApplicantCountPort;
import com._2carrot84.kstd.lecture.application.port.out.SavePopularLecturePort;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PopularLecturePersistenceAdapter implements SavePopularLecturePort, IncreaseApplicantCountPort,
	DecreaseApplicantCountPort, DeleteAllPopularLecturePort, FindPopularLecturesPort {

	private final PopularLectureRepository popularLectureRepository;

	@Override
	public void save(PopularLecture lecture) {
		popularLectureRepository.save(lecture.getLectureId(), lecture.getCount());
	}

	@Override
	public void decreaseApplicantCount(Long lectureId) {
		popularLectureRepository.decreaseApplicantCount(lectureId);
	}

	@Override
	public void deleteAll() {
		popularLectureRepository.deleteAll();
	}

	@Override
	public void increaseApplicantCount(Long lectureId) {
		popularLectureRepository.increaseApplicantCount(lectureId);
	}

	@Override
	public List<PopularLecture> findPopularLectures() {
		return popularLectureRepository.findAll()
			.stream()
			.map(lecture -> PopularLecture.builder()
				.lectureId(lecture.getLectureId())
				.count(lecture.getCount())
				.build())
			.toList();
	}
}
